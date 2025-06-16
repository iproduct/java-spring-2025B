import course.spring.model.Person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("John", "Smith", LocalDate.of(1978, 7, 18)));
        persons.add(new Person("Ivan", "Petrov", LocalDate.of(1982, 8, 3)));
        persons.add(new Person("Maria", "Hristova", LocalDate.of(1995, 2, 11)));
//        persons.forEach(System.out::println);

        for(Person p : persons) {
            System.out.println(p);
        }
        System.out.println("====================================");
        for(int i = 0; i < persons.size(); i++){
            System.out.println(persons.get(i));
        }
    }
}