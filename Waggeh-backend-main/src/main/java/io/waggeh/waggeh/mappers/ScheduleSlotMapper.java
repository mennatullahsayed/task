package io.waggeh.waggeh.mappers;

import io.waggeh.waggeh.constant.SlotStatus;
import io.waggeh.waggeh.domain.ScheduleSlot;
import io.waggeh.waggeh.domain.User;
import io.waggeh.waggeh.exception.SlotExpiredException;
import io.waggeh.waggeh.exception.SlotHeldException;
import io.waggeh.waggeh.model.ScheduleSlotDTO;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ScheduleSlotMapper {
    private final static DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static ScheduleSlot toEntity(ScheduleSlotDTO scheduleSlotDTO) {
        final ScheduleSlot scheduleSlot = new ScheduleSlot();
        BeanUtils.copyProperties(scheduleSlotDTO,scheduleSlot);
        scheduleSlot.setStartTime(LocalDateTime.parse(scheduleSlotDTO.getStartTime(),sdf));
        scheduleSlot.setEndTime(LocalDateTime.parse(scheduleSlotDTO.getEndTime(),sdf));
        return scheduleSlot;
    }

    public static ScheduleSlotDTO toDTO(ScheduleSlot scheduleSlot) {
        final ScheduleSlotDTO scheduleSlotDTO = new ScheduleSlotDTO();
        scheduleSlotDTO.setStartTime(scheduleSlot.getStartTime().format(sdf));
        scheduleSlotDTO.setEndTime(scheduleSlot.getEndTime().format(sdf));
        scheduleSlotDTO.setId(scheduleSlot.getId());
        scheduleSlotDTO.setStatus(scheduleSlot.getStatus());
        return scheduleSlotDTO;
    }
    public static List<ScheduleSlotDTO> toDTOList(List<ScheduleSlot> scheduleSlots) {
        final List<ScheduleSlotDTO> scheduleSlotDTOs = new ArrayList<>();
        scheduleSlots.forEach(scheduleSlot -> {
            ScheduleSlotDTO scheduleSlotDTO = new ScheduleSlotDTO();
            scheduleSlotDTO.setStartTime(scheduleSlot.getStartTime().format(sdf));
            scheduleSlotDTO.setEndTime(scheduleSlot.getEndTime().format(sdf));
            scheduleSlotDTO.setId(scheduleSlot.getId());
            scheduleSlotDTO.setStatus(scheduleSlot.getStatus());
            scheduleSlotDTOs.add(scheduleSlotDTO);
        });

        return scheduleSlotDTOs;
    }

    public static List<ScheduleSlot> generateSlots(ScheduleSlot scheduleSlot,User consultant) {
        List<ScheduleSlot> slots = new ArrayList<>();

        LocalDateTime currentSlotStartTime = scheduleSlot.getStartTime();
        while (currentSlotStartTime.isBefore(scheduleSlot.getEndTime())) {
            LocalDateTime currentSlotEndTime = currentSlotStartTime.plusMinutes(15);

            if (currentSlotEndTime.isAfter(scheduleSlot.getEndTime())) {
                currentSlotEndTime = scheduleSlot.getEndTime();
            }

            ScheduleSlot slot = new ScheduleSlot();
            slot.setStartTime(currentSlotStartTime);
            slot.setEndTime(currentSlotEndTime);
            slot.setUserId(consultant);
            slot.setStatus(SlotStatus.FREE);

            slots.add(slot);

            // Move to the next slot
            currentSlotStartTime = currentSlotEndTime;
        }

        return slots;
    }

    public static List<ScheduleSlot> toExpiredSlots(List<ScheduleSlot> scheduleSlots) {
        final List<ScheduleSlot> updatedScheduleSlots = new ArrayList<>();
        scheduleSlots.forEach(scheduleSlot -> {
            scheduleSlot.setStatus(SlotStatus.EXPIRED);
            updatedScheduleSlots.add(scheduleSlot);
        });

        return updatedScheduleSlots;
    }

    public static List<ScheduleSlot> toFreeSlots(List<ScheduleSlot> scheduleSlots) {
        final List<ScheduleSlot> updatedScheduleSlots = new ArrayList<>();
        scheduleSlots.forEach(scheduleSlot -> {
            scheduleSlot.setStatus(SlotStatus.FREE);
            updatedScheduleSlots.add(scheduleSlot);
        });

        return updatedScheduleSlots;
    }

    public static List<ScheduleSlot> toHeldSlots(List<ScheduleSlot> scheduleSlots) {
        final List<ScheduleSlot> updatedScheduleSlots = new ArrayList<>();
        scheduleSlots.forEach(scheduleSlot -> {
            if(scheduleSlot.getStatus().equals(SlotStatus.EXPIRED)){
                throw new SlotExpiredException();
            }else if(scheduleSlot.getStatus().equals(SlotStatus.HOLD)){
                throw new SlotHeldException();
            }else {
                scheduleSlot.setStatus(SlotStatus.HOLD);
                updatedScheduleSlots.add(scheduleSlot);
            }

        });

        return updatedScheduleSlots;
    }
}
