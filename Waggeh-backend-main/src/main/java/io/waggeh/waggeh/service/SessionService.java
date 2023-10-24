package io.waggeh.waggeh.service;

import io.waggeh.waggeh.config.GoogleEmailConfig;
import io.waggeh.waggeh.constant.SessionType;
import io.waggeh.waggeh.constant.UserRole;
import io.waggeh.waggeh.domain.Field;
import io.waggeh.waggeh.domain.ScheduleSlot;
import io.waggeh.waggeh.domain.Session;
import io.waggeh.waggeh.domain.User;
import io.waggeh.waggeh.exception.SlotExpiredException;
import io.waggeh.waggeh.exception.SlotHeldException;
import io.waggeh.waggeh.mappers.ScheduleSlotMapper;
import io.waggeh.waggeh.mappers.SessionMapper;
import io.waggeh.waggeh.model.SessionDTO;
import io.waggeh.waggeh.model.requests.ZoomMeetingRequest;
import io.waggeh.waggeh.model.responses.ApiResponseWrapper;
import io.waggeh.waggeh.model.responses.SessionInfo;
import io.waggeh.waggeh.repos.FieldRepository;
import io.waggeh.waggeh.repos.ScheduleSlotRepository;
import io.waggeh.waggeh.repos.SessionRepository;
import io.waggeh.waggeh.repos.UserRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;


@Service
public class SessionService {

    private final SessionRepository sessionRepository;
    private final FieldRepository fieldRepository;
    private final ScheduleSlotRepository scheduleSlotRepository;
    private final UserRepository userRepository;

    private final ZoomMeetingService zoomMeetingService;

    private final GoogleEmailConfig googleEmailConfig;
    public SessionService(final SessionRepository sessionRepository,
                          final FieldRepository fieldRepository,
                          final ScheduleSlotRepository scheduleSlotRepository,
                          final UserRepository userRepository, ZoomMeetingService zoomMeetingService, GoogleEmailConfig googleEmailConfig) {
        this.sessionRepository = sessionRepository;
        this.fieldRepository = fieldRepository;
        this.scheduleSlotRepository = scheduleSlotRepository;
        this.userRepository = userRepository;
        this.zoomMeetingService = zoomMeetingService;
        this.googleEmailConfig = googleEmailConfig;
    }

    public ApiResponseWrapper<SessionDTO> createSession(SessionDTO sessionDTO){
        ApiResponseWrapper<SessionDTO> responseWrapper = new ApiResponseWrapper<>();
       try {
           DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

           Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(!authentication.isAuthenticated()){
                responseWrapper.setSuccess(false);
                responseWrapper.setMessage("Token is not valid!");
                responseWrapper.setPayload(null);
                return responseWrapper;
            }
            String userEmail = authentication.getName();
            final Optional<User> optionalUser = userRepository.findUserByEmail(userEmail);
            if (!optionalUser.isPresent()) {
                responseWrapper.setSuccess(false);
                responseWrapper.setMessage("User not found");
                responseWrapper.setPayload(null);
                return responseWrapper;
            }else {
                User client = optionalUser.get();
                if (!client.getRole().equals(UserRole.ROLE_CLIENT)) {
                    responseWrapper.setSuccess(false);
                    responseWrapper.setMessage("You are not a client");
                    responseWrapper.setPayload(null);
                    return responseWrapper;
                }
                Optional<Field> optionalField = fieldRepository.findById(sessionDTO.getFieldId());
                if(!optionalField.isPresent()){
                    responseWrapper.setSuccess(false);
                    responseWrapper.setMessage("Category Not Exist");
                    responseWrapper.setPayload(null);
                    return responseWrapper;
                }
                Field field = optionalField.get();

                Optional<User> optionalConsultant = userRepository.findById(sessionDTO.getConsultantId());
                if(!optionalConsultant.isPresent()){
                    responseWrapper.setSuccess(false);
                    responseWrapper.setMessage("Consultant Not Exist");
                    responseWrapper.setPayload(null);
                    return responseWrapper;
                }
                User consultant = optionalConsultant.get();
                Set<User> users = new HashSet<>();
                users.add(consultant);
                users.add(client);
                Session session = SessionMapper.toEntity(sessionDTO);
                session.setUsers(users);
                session.setUsers(users);
                session.setFieldId(field);
                session.setStatus(SessionType.CREATED);
                AtomicReference<List<ScheduleSlot>> scheduleSlots = new AtomicReference<>(new ArrayList<>());
                sessionDTO.getSlots().forEach(scheduleSlot -> {
                    Optional<ScheduleSlot> scheduleSlotOptional = scheduleSlotRepository.findById(scheduleSlot.getId());
                    scheduleSlotOptional.ifPresent(slot -> scheduleSlots.get().add(slot));
                });
                List<ScheduleSlot> sessionSlots = ScheduleSlotMapper.toHeldSlots(scheduleSlots.get());
                scheduleSlotRepository.saveAll(sessionSlots);
                session.setScheduleSlots(sessionSlots);
                List<ScheduleSlot> sortedSlots = scheduleSlots
                        .get()
                        .stream()
                        .sorted(Comparator.comparing(ScheduleSlot::getStartTime))
                        .toList();
                int slotsCount =  sessionSlots.size();
                String meetingStartTime = sortedSlots.get(0).getStartTime().format(formatter);
                String meetingEndTime = sortedSlots.get(slotsCount-1).getEndTime().format(formatter);
                Integer meetingDuration = slotsCount*15;
                String meetingTopic = "Meeting With ".concat(consultant.getFirstName()).concat(" powered by Waggeh.");
                ZoomMeetingRequest zoomMeetingRequest = new ZoomMeetingRequest();

                zoomMeetingRequest.setStartTime(meetingStartTime);
                zoomMeetingRequest.setTopic(meetingTopic);
                ApiResponseWrapper<Map<String,String>> meetingResponse = zoomMeetingService.creatMeeting(zoomMeetingRequest);

                if(!meetingResponse.getSuccess()){
                    responseWrapper.setSuccess(false);
                    responseWrapper.setMessage("Can't Creating Meeting because of : ".concat(meetingResponse.getMessage()));
                    responseWrapper.setPayload(null);
                    return responseWrapper;
                }
                String clientMeetingURL = meetingResponse.getPayload().get("clientURL");
                String consultantMeetingURL = meetingResponse.getPayload().get("consultantURL");

                session.setClientMeetingURL(clientMeetingURL);
                session.setConsultantMeetingURL(consultantMeetingURL);
                session.setMeetingStartTime(meetingStartTime);
                session.setMeetingDuration(meetingDuration);
                session.setMeetingEndTime(meetingEndTime);

                Session savedSession = sessionRepository.save(session);

                SessionDTO sessionDTOResponse = SessionMapper.toDTO(savedSession);
                sessionDTOResponse.setConsultantId(sessionDTO.getConsultantId());
                List<Session> clientSessions = client.getSessions();
                List<Session> consultantSessions = consultant.getSessions();
                clientSessions.add(session);
                consultantSessions.add(session);
                userRepository.save(client);
                userRepository.save(consultant);

                //:TODO send email
                SimpleMailMessage clientMessage = new SimpleMailMessage();
                clientMessage.setFrom("waggeh.consultancy@gmail.com");
                clientMessage.setTo(client.getEmail());
                clientMessage.setSubject("Waggeh Meeting with : ".concat(consultant.getFirstName()));
                clientMessage.setText("The meeting created with our consultant : "
                        .concat(consultant.getFirstName()).concat(" ")
                        .concat(consultant.getLastName())
                        .concat(" as a ")
                        .concat(field.getDisplayName())
                        .concat(" expert consultant at : ")
                        .concat(meetingStartTime.replace('T',' ').replace('Z',' '))
                        .concat("  .\n").concat(".")
                        .concat("You can join the meeting from the bellow link \n\n")
                        .concat("Meeting URL : ").concat(clientMeetingURL));

                googleEmailConfig.getJavaMailSender().send(clientMessage);


                SimpleMailMessage consultantMessage = new SimpleMailMessage();
                consultantMessage.setFrom("waggeh.consultancy@gmail.com");
                consultantMessage.setTo(consultant.getEmail());
                consultantMessage.setSubject("Waggeh Meeting with : ".concat(consultant.getFirstName()));
                consultantMessage.setText("The meeting created with our consultant : "
                        .concat(consultant.getFirstName()).concat(" ")
                        .concat(consultant.getLastName())
                        .concat(" as a ")
                        .concat(field.getDisplayName())
                        .concat(" expert consultant at : ")
                        .concat(meetingStartTime.replace('T',' ').replace('Z',' '))
                        .concat(".")
                        .concat("You can join the meeting from the bellow link \n")
                        .concat("Meeting URL : ").concat(consultantMeetingURL));

                googleEmailConfig.getJavaMailSender().send(consultantMessage);

                responseWrapper.setSuccess(true);
                responseWrapper.setMessage("Session Booked Successfully");
                responseWrapper.setPayload(sessionDTOResponse);
                return responseWrapper;
            }
        }catch (SlotExpiredException ex){
           return new ApiResponseWrapper<>(false, "Can't Select Expired slots!");
       } catch (SlotHeldException ex){
           return new ApiResponseWrapper<>(false, "Can't Select Held slots!");
       } catch (Exception e) {
            return new ApiResponseWrapper<>(false, e.getMessage(), null);
        }
    }

    public ApiResponseWrapper<Object> cancelSession(String sessionId){
        Optional<Session> canceledSessionOptional = sessionRepository.findById(sessionId);
        if(!canceledSessionOptional.isPresent()){
            return new ApiResponseWrapper<>(false,"Session is Not Exist!");
        }
        Session cancledSession = canceledSessionOptional.get();
        List<ScheduleSlot> scheduleSlots = ScheduleSlotMapper.toFreeSlots(cancledSession.getScheduleSlots());
        scheduleSlotRepository.saveAll(scheduleSlots);
        cancledSession.setStatus(SessionType.CANCELED);
        sessionRepository.save(cancledSession);
        return new ApiResponseWrapper<>(true,"Session canceled successfully");
    }
    public ApiResponseWrapper<List<SessionInfo>> getAllSessions(){
        ApiResponseWrapper<List<SessionInfo>> responseWrapper = new ApiResponseWrapper<>();
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(!authentication.isAuthenticated()){
                responseWrapper.setSuccess(false);
                responseWrapper.setMessage("Token is not valid!");
                responseWrapper.setPayload(null);
                return responseWrapper;
            }
            String userEmail = authentication.getName();
            final Optional<User> optionalUser = userRepository.findUserByEmail(userEmail);
            if (!optionalUser.isPresent()) {
                responseWrapper.setSuccess(false);
                responseWrapper.setMessage("User not found");
                responseWrapper.setPayload(null);
                return responseWrapper;
            }
            User client = optionalUser.get();
            List<SessionInfo> sessionInfos  = client.getSessions().stream().map(SessionMapper::toInfo).toList();
            return new ApiResponseWrapper<>(true, "Get All Sessions Successfully!", sessionInfos);
        }catch (Exception e) {
            return new ApiResponseWrapper<>(false, e.getMessage(), null);
        }
    }

}
