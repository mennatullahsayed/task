package io.waggeh.waggeh.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import io.waggeh.waggeh.constant.SlotStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Set;


@Document
@Getter
@Setter
public class ScheduleSlot {


    @Id
    private String id;

    @NotNull
    private LocalDateTime startTime;

    @NotNull
    private LocalDateTime endTime;


    @DocumentReference(lazy = true)
    private User userId;

    @DocumentReference(lazy = true, lookup = "{ 'scheduleSlotId' : ?#{#self._id} }")
    @ReadOnlyProperty
    @JsonBackReference
    private Set<Session> sessions;

    private SlotStatus status;

    @CreatedDate
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    private OffsetDateTime lastUpdated;

    @Version
    private Integer version;

}
