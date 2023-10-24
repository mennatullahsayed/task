package io.waggeh.waggeh.model;


import io.waggeh.waggeh.constant.SessionType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


@Getter
@Setter
public class SessionDTO {


    private String id;

    private Double userPrice;

    private Double consultantPrice;

    private Double price;

    private SessionType status;

    private String fieldId;

    private List<ScheduleSlotDTO> slots;

    private String clientMeetingURL;
    private String consultantMeetingURL;
    private String meetingStartTime;
    private Integer meetingDuration;
    private String meetingEndTime;

    private String consultantId;

}
