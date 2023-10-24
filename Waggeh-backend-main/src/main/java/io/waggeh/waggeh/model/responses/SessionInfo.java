package io.waggeh.waggeh.model.responses;

import io.waggeh.waggeh.constant.SessionType;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SessionInfo {
    private String id;

    private Double userPrice;

    private Double consultantPrice;

    private Double price;

    private SessionType status;

    private String fieldId;
    private String fieldName;

    private String clientMeetingURL;
    private String consultantMeetingURL;
    private String meetingStartTime;
    private Integer meetingDuration;
    private String meetingEndTime;

    private String consultantId;
    private String consultantTitle;
    private String consultantName;
    private String consultantImageURL;

    private String clientId;
    private String clientName;
    private String clientImageURL;
}
