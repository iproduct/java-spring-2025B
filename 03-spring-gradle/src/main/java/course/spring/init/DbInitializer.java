package course.spring.init;

import course.spring.dao.UserRepository;
import course.spring.model.Role;
import course.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DbInitializer implements ApplicationRunner {
    private static final List<User> USERS = List.of(
            new User("Ivan", "Petrov", LocalDate.of(1978, 5, 17),
                    "ivan", "ivan123", Role.READER, "ivan@gmail.com"),
            new User("John", "Smith", LocalDate.of(1982, 7, 3),
                    "sjohn", "john123", Role.AUTHOR, "john@gmail.com"),
            new User("Mary", "Smith", LocalDate.of(1985, 4, 21),
                    "mary", "mary123", Role.ADMIN, "mary@yahoo.com"),
            new User("Zornica", "Dimitrova", LocalDate.of(1987, 4, 17),
                    "moni", "mary123", Role.READER, "mony@yahoo.com"),
            new User("Dimitar", "Dimitrov", LocalDate.of(1987, 4, 17),
                    "moni", "mary123", Role.AUTHOR, "mony@yahoo.com"),
            new User("Monica", "Dimitrova", LocalDate.of(1987, 4, 17),
                    "moni", "mary123", Role.ADMIN, "mony@yahoo.com"),
            new User("Maya", "Smith", LocalDate.of(1984, 8, 8),
                    "maya", "mary123", Role.READER, "maya@yahoo.com"),
            new User("Maya", "Hristova", LocalDate.of(1987, 7, 29),
                    "maya2", "mary123", Role.READER, "mayah@yahoo.com")
    );

    @Autowired
    private UserRepository userRepo;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        USERS.forEach(userRepo::create);
    }
}
