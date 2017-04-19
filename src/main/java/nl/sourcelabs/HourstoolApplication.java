package nl.sourcelabs;

import nl.sourcelabs.domain.User;
import nl.sourcelabs.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class HourstoolApplication {

    public static void main(String[] args) {
        SpringApplication.run(HourstoolApplication.class, args);
        List<User> users;
        users = Arrays.asList(
                new User("voornaam1", "achternaam1", "username1", "someone@somewhere.org"),
                new User("voornaam2", "achternaam2", "username2", "someoneElse@somewhere.org")
        );

//        System.out.println(
//                users.stream().filter(e -> e.getFirstName().equalsIgnoreCase("voornaam1")).findAny().orElseGet(null)
//        );
    }

    @Bean
    CommandLineRunner initData(UserRepository userRepository) {
        return args -> {
            userRepository.save(new User("voornaam1", "achternaam1", "username1", "someone@somewhere.org"));
            userRepository.save(new User("voornaam2", "achternaam2", "username2", "someoneElse@somewhere.org"));

//            System.out.println(userRepository.findUserByUsername("username1").getFirstName());

        };
    }
}
