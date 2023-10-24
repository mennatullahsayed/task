package io.waggeh.waggeh.repos;

import io.waggeh.waggeh.domain.RefreshToken;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends MongoRepository<RefreshToken,String> {
    void deleteByOwner_Id(Long id);
    default void deleteByOwner_Id(String id) {
        deleteByOwner_Id(id);
    };
}
