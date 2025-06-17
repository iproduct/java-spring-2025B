import course.spring.dao.Repository;
import course.spring.dao.UserRepository;
import course.spring.dao.impl.LongIdGenerator;
import course.spring.dao.impl.RepositoryInMemory;
import course.spring.dao.impl.UserRepositoryInMemory;
import course.spring.model.Person;
import course.spring.model.Role;
import course.spring.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static course.spring.model.Role.*;

public class Main {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        users.add(new User("John", "Smith", "john", "john123"));
        users.add(new User("Ivan", "Petrov", "ivan", "ivan123"));
        users.add(new User("Maria", "Hristova", "maria", "maria123"));
//        persons.add(new Person("Nedyalko", "Dimitrov", LocalDate.of(1979, 7,13)));
        users.add(new User("Maya", "Dimitrova", LocalDate.of(1984, 8,19),
                "maya", "maya123", ADMIN, "maya@gmail.com"));
//        user.forEach(System.out::println);

        // Create UserRepository with LongIdGenerator
        UserRepository userRepo = new UserRepositoryInMemory(new LongIdGenerator());

        for(User u : users) {
            userRepo.create(u);
        }

        // Get all users from repo
        var allUsers = userRepo.findAll();
        allUsers.forEach(System.out::println);

        // Find user by username
        System.out.println(userRepo.findByUsername("maya"));

    }
}