import course.spring.model.Person;
import course.spring.model.Role;
import course.spring.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static course.spring.model.Role.*;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();
        persons.add(new User("John", "Smith", "john", "john123"));
        persons.add(new User("Ivan", "Petrov", "ivan", "ivan123"));
        persons.add(new User("Maria", "Hristova", "maria", "maria123"));
        persons.add(new Person("Nedyalko", "Dimitrov", LocalDate.of(1979, 7,13)));
        persons.add(new User("Maya", "Dimitrova", LocalDate.of(1984, 8,19),
                "maya", "maya123", ADMIN, "maya@gmail.com"));
//        user.forEach(System.out::println);

        for(Person p : persons) {
            System.out.println(p.toString());
        }

    }
}