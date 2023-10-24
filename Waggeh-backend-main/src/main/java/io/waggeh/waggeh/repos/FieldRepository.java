package io.waggeh.waggeh.repos;

import io.waggeh.waggeh.domain.Field;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface FieldRepository extends MongoRepository<Field, String> {

    boolean existsByIdIgnoreCase(String id);

    boolean existsByNameIgnoreCase(String name);

}
