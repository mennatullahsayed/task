package io.waggeh.waggeh.service;

import io.waggeh.waggeh.constant.SlotStatus;
import io.waggeh.waggeh.constant.UserRole;
import io.waggeh.waggeh.domain.ScheduleSlot;
import io.waggeh.waggeh.domain.User;
import io.waggeh.waggeh.mappers.ScheduleSlotMapper;
import io.waggeh.waggeh.model.ScheduleSlotDTO;
import io.waggeh.waggeh.model.requests.CreateConsultantSchedule;
import io.waggeh.waggeh.model.responses.ApiResponseWrapper;
import io.waggeh.waggeh.repos.ScheduleSlotRepository;
import io.waggeh.waggeh.repos.UserRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


@Service
public class ScheduleSlotService {

    private final ScheduleSlotRepository scheduleSlotRepository;
    private final UserRepository userRepository;

    public ScheduleSlotService(final ScheduleSlotRepository scheduleSlotRepository,
            final UserRepository userRepository) {
        this.scheduleSlotRepository = scheduleSlotRepository;
        this.userRepository = userRepository;
    }


    public ApiResponseWrapper createSlots(final CreateConsultantSchedule scheduleSlotDTOs) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(!authentication.isAuthenticated()){
                return new ApiResponseWrapper<String>(false, "Token is not valid!", null);
            }
            String userEmail = authentication.getName();
            final Optional<User> optionalUser = userRepository.findUserByEmail(userEmail);
            if (!optionalUser.isPresent()) {
                return new ApiResponseWrapper<String>(false, "Consultant not found", null);
            }else {
                User consultant = optionalUser.get();
                if (!consultant.getRole().equals(UserRole.ROLE_CONSULTANT)) {
                    return new ApiResponseWrapper<>(false, "You are not a consultant", null);
                }
                //Updating old slots to be expired
                List<ScheduleSlot> expiredSlots = scheduleSlotRepository.getAllByUserIdAndEndTimeBefore(consultant, LocalDateTime.now());
                if(!expiredSlots.isEmpty()){
                    scheduleSlotRepository.saveAll(ScheduleSlotMapper.toExpiredSlots(expiredSlots));
                }

                List<ScheduleSlot> scheduleSlots = new ArrayList<>();
                for (ScheduleSlotDTO slotDTO : scheduleSlotDTOs.getScheduleSlots()) {
                    ScheduleSlot scheduleSlot = ScheduleSlotMapper.toEntity(slotDTO);
                    scheduleSlots.addAll(ScheduleSlotMapper.generateSlots(scheduleSlot,consultant));
                }
                scheduleSlotRepository.saveAll(scheduleSlots);
                return new ApiResponseWrapper<>(true,"Time slots saved successfully",null);
            }
        }catch (Exception e) {
            return new ApiResponseWrapper<>(false, e.getMessage(), null);
        }

    }
    public ApiResponseWrapper<List<ScheduleSlotDTO>> getAllFreeSlots(String consultantId){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(!authentication.isAuthenticated()){
                return new ApiResponseWrapper<List<ScheduleSlotDTO>>(false, "Token is not valid!", null);
            }
            final Optional<User> optionalUser = userRepository.findById(consultantId);
            if (!optionalUser.isPresent()) {
                return new ApiResponseWrapper<List<ScheduleSlotDTO>>(false, "Consultant not found", null);
            }else {
                User consultant = optionalUser.get();
                List<ScheduleSlot> allSlots = scheduleSlotRepository.findAllByUserIdAndStatus(consultant, SlotStatus.FREE);

                List<ScheduleSlotDTO> scheduleSlotDTOs = new ArrayList<>();
                for (ScheduleSlot slot : allSlots) {
                    ScheduleSlotDTO scheduleSlotDTO = ScheduleSlotMapper.toDTO(slot);
                    scheduleSlotDTOs.add(scheduleSlotDTO);
                }
                return new ApiResponseWrapper<>(true,"Success",scheduleSlotDTOs);
            }
        }catch (Exception e) {
            return new ApiResponseWrapper<>(false, e.getMessage(), null);
        }
    }
    @Scheduled(fixedRate = 60000)
    private void updateSlots(){

        List<ScheduleSlot> freeSlots = scheduleSlotRepository.getAllByEndTimeBeforeAndStatus(LocalDateTime.now(),SlotStatus.FREE);
        System.out.println("updateSlots update : "+freeSlots.size()+" slot");
        if(!freeSlots.isEmpty()){
            scheduleSlotRepository.saveAll(ScheduleSlotMapper.toExpiredSlots(freeSlots));
        }
    }



}
