package io.waggeh.waggeh.repos;

import io.waggeh.waggeh.constant.SlotStatus;
import io.waggeh.waggeh.domain.ScheduleSlot;
import io.waggeh.waggeh.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


public interface ScheduleSlotRepository extends MongoRepository<ScheduleSlot, String> {

    boolean existsByIdIgnoreCase(String id);
    void deleteAllByUserIdAndEndTimeBefore(User userId, LocalDateTime endTime);
    List<ScheduleSlot> getAllByUserIdAndEndTimeBefore(User userId, LocalDateTime endTime);

    List<ScheduleSlot> findAllByUserIdAndStatus(User  userId, SlotStatus status);


    List<ScheduleSlot> getAllByEndTimeBeforeAndStatus(LocalDateTime endTime,SlotStatus status);
}
