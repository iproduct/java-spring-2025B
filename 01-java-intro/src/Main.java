import course.spring.model.Person;
import course.spring.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<User> user = new ArrayList<>();
        user.add(new User("John", "Smith", "john", "john123"));
        user.add(new User("Ivan", "Petrov", "ivan", "ivan123"));
        user.add(new User("Maria", "Hristova", "maria", "maria123"));
//        user.forEach(System.out::println);

        for(User u : user) {
            System.out.println(u);
        }
        System.out.println("====================================");
        for(int i = 0; i < user.size(); i++){
            System.out.println(user.get(i));
        }
    }
}