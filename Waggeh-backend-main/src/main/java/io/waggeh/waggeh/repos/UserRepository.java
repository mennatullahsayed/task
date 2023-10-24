package io.waggeh.waggeh.repos;

import io.waggeh.waggeh.constant.UserRole;
import io.waggeh.waggeh.domain.Field;
import io.waggeh.waggeh.domain.Session;
import io.waggeh.waggeh.domain.User;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<User, String> {
    boolean existsByIdIgnoreCase(String id);

    boolean existsByEmailIgnoreCase(String email);
    List<User> findAllByFullNameContainingIgnoreCaseAndRole(String name,UserRole role);
    boolean existsByPhoneIgnoreCase(String phone);

    boolean existsByLinkedInUrlIgnoreCase(String linkedInUrl);

    List<User> findAllBySessions(Session session);

    Optional<User> findByUsername(String username);
    Optional<User> findUserByEmail(String email);

    boolean existsByEmailOrPhone(String email, String phone);
    boolean existsByEmail(String email);

    List<User> findFirst5ByRoleAndFieldId(UserRole userRole, Field field);
    List<User> findAllByRoleAndFieldId(UserRole userRole, Field field);
    List<User> findAllByRole(UserRole userRole);

}
