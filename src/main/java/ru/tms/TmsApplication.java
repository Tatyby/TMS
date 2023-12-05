package ru.tms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TmsApplication.class, args);
    }
//    @Bean
//    CommandLineRunner commandLineRunner(UserRepository users) {
//        return args -> users.save(new TmsUser(1,"user",null, "user"));
//    }

}
