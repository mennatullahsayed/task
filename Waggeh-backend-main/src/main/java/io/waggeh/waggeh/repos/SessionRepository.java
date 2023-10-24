package io.waggeh.waggeh.repos;

import io.waggeh.waggeh.domain.Session;
import io.waggeh.waggeh.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface SessionRepository extends MongoRepository<Session, String> {

    boolean existsByIdIgnoreCase(String id);
    List<Session> findAllByUsersContains(User user);
    List<Session> findAllByUsersContaining(User user);

}
