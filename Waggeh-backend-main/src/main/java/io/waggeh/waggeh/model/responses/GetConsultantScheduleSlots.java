package io.waggeh.waggeh.model.responses;

import io.waggeh.waggeh.model.ScheduleSlotDTO;

import java.util.ArrayList;
import java.util.List;

public class GetConsultantScheduleSlots {
    private List<ScheduleSlotDTO> scheduleSlots;

    public GetConsultantScheduleSlots() {
        this.scheduleSlots = new ArrayList<>();
    }

    public GetConsultantScheduleSlots(List<ScheduleSlotDTO> scheduleSlots) {
        this.scheduleSlots = scheduleSlots;
    }

    public void setScheduleSlots(List<ScheduleSlotDTO> scheduleSlots) {
        this.scheduleSlots = scheduleSlots;
    }

    public List<ScheduleSlotDTO> getScheduleSlots() {
        return scheduleSlots;
    }
}
