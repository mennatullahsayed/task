package io.waggeh.waggeh.mappers;

import io.waggeh.waggeh.domain.User;
import io.waggeh.waggeh.model.SessionDTO;
import io.waggeh.waggeh.model.UserDTO;
import org.springframework.beans.BeanUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class UserMapper {
    public static User toEntity(UserDTO userDTO) {
        final User user = new User();
        BeanUtils.copyProperties(userDTO,user);
        return user;
    }
    public static UserDTO toDTO(User user) {
        final UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user,userDTO);

        if(Objects.nonNull(user.getFieldId())){
            userDTO.setFieldId(FieldMapper.toDTO(user.getFieldId()));
        }

        if(!user.getScheduleSlots().isEmpty()){
//            userDTO.setScheduleSlots(ScheduleSlotMapper.toDTOList(user.getScheduleSlots()));
        }
        if(!user.getSessions().isEmpty()){
//            AtomicReference<List<SessionDTO>> sessionsSet = new AtomicReference<>(new ArrayList<>());
//            user.getSessions().forEach(session -> sessionsSet.get().add(SessionMapper.toDTO(session)));
//            userDTO.setSessions(sessionsSet.get());
        }
        return userDTO;
    }
}
