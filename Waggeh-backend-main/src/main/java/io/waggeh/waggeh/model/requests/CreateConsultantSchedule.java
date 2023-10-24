package io.waggeh.waggeh.model.requests;

import io.waggeh.waggeh.model.ScheduleSlotDTO;

import java.util.ArrayList;
import java.util.List;

public class CreateConsultantSchedule {
    private List<ScheduleSlotDTO> scheduleSlots;

    public CreateConsultantSchedule() {
        this.scheduleSlots = new ArrayList<>();
    }

    public CreateConsultantSchedule(List<ScheduleSlotDTO> scheduleSlots) {
        this.scheduleSlots = scheduleSlots;
    }

    public void setScheduleSlots(List<ScheduleSlotDTO> scheduleSlots) {
        this.scheduleSlots = scheduleSlots;
    }

    public List<ScheduleSlotDTO> getScheduleSlots() {
        return scheduleSlots;
    }
}
