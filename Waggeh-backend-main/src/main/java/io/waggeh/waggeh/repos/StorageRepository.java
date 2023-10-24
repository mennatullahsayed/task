package io.waggeh.waggeh.repos;


import io.waggeh.waggeh.domain.Image;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StorageRepository extends MongoRepository<Image, String> {

        boolean existsByIdIgnoreCase(String id);

        boolean existsByNameIgnoreCase(String name);

        Optional<Image> findByName(String name);
}
