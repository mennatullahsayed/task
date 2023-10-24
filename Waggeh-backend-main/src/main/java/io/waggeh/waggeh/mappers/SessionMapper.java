package io.waggeh.waggeh.mappers;

import io.waggeh.waggeh.constant.UserRole;
import io.waggeh.waggeh.domain.Field;
import io.waggeh.waggeh.domain.Session;
import io.waggeh.waggeh.domain.User;
import io.waggeh.waggeh.model.SessionDTO;
import io.waggeh.waggeh.model.responses.SessionInfo;
import org.springframework.beans.BeanUtils;

import java.util.Objects;
import java.util.Set;

public class SessionMapper {
    public static Session toEntity(SessionDTO sessionDTO) {
        final Session session = new Session();
        BeanUtils.copyProperties(sessionDTO,session);
        return session;
    }

    public static SessionDTO toDTO(Session session) {
        final SessionDTO sessionDTO = new SessionDTO();
        BeanUtils.copyProperties(session,sessionDTO);
        sessionDTO.setMeetingStartTime(session.getMeetingStartTime().replace('T',' ').replace('Z',' '));
        sessionDTO.setMeetingEndTime(session.getMeetingEndTime().replace('T',' ').replace('Z',' '));
        if(Objects.nonNull(session.getFieldId())){
            sessionDTO.setFieldId(session.getFieldId().getId());
        }
        if(!session.getScheduleSlots().isEmpty()){
            sessionDTO.setSlots(ScheduleSlotMapper.toDTOList(session.getScheduleSlots()));
        }

        return sessionDTO;
    }

    public static SessionInfo toInfo(Session session) {
        final SessionInfo sessionInfo = new SessionInfo();
        BeanUtils.copyProperties(session,sessionInfo);
        sessionInfo.setMeetingStartTime(session.getMeetingStartTime().replace('T',' ').replace('Z',' '));
        sessionInfo.setMeetingEndTime(session.getMeetingEndTime().replace('T',' ').replace('Z',' '));
        if(Objects.nonNull(session.getFieldId())){
            Field field = session.getFieldId();
            sessionInfo.setFieldId(field.getId());
            sessionInfo.setFieldName(field.getDisplayName());
        }
        if(!session.getUsers().isEmpty()){
            Set<User> users = session.getUsers();
            users.forEach(user -> {
                if(user.getRole().equals(UserRole.ROLE_CONSULTANT)){
                    sessionInfo.setConsultantId(user.getId());
                    sessionInfo.setConsultantName(user.getFirstName()+" "+user.getLastName());
                    sessionInfo.setConsultantTitle(user.getTitle());
                    sessionInfo.setConsultantImageURL(user.getPhotoUrl());
                }else if(user.getRole().equals(UserRole.ROLE_CLIENT)){
                    sessionInfo.setClientId(user.getId());
                    sessionInfo.setClientName(user.getFirstName()+" "+user.getLastName());
                    sessionInfo.setClientImageURL(user.getPhotoUrl());
                }
            });
        }


        return sessionInfo;
    }

}
