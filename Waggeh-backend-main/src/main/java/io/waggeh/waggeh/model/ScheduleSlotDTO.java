package io.waggeh.waggeh.model;

   
import io.waggeh.waggeh.constant.SlotStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleSlotDTO {

    private String id;

    private String startTime;

    private String endTime;
    private SlotStatus status;


}
