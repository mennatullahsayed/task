package io.waggeh.waggeh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
//@EnableMongoRepositories
@EnableScheduling
public class WaggehApplication {

    public static void main(final String[] args) {
        SpringApplication.run(WaggehApplication.class, args);
    }

}
