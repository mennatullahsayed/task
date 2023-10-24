package io.waggeh.waggeh.domain;


import io.waggeh.waggeh.constant.SessionType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;


@Document
@Getter
@Setter
public class Session {

    @Id
    private String id;

    private Double userPrice;

    private Double consultantPrice;

    @NotNull
    private Double price;


    @NotNull
    private SessionType status;

    @DocumentReference(lazy = true)
    private Field fieldId;

    @DocumentReference(lazy = true, lookup = "{ 'sessions' : ?#{#self._id} }")
    @ReadOnlyProperty
    private Set<User> users;

    @DocumentReference(lazy = true)
    private List<ScheduleSlot> scheduleSlots;

    private String consultantMeetingURL;
    private String clientMeetingURL;
    private String meetingStartTime;
    private Integer meetingDuration;
    private String meetingEndTime;

    @CreatedDate
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    private OffsetDateTime lastUpdated;

    @Version
    private Integer version;

}
