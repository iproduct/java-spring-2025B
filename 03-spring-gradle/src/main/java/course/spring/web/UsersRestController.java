package course.spring.web;

import course.spring.dao.UserRepository;
import course.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersRestController {
    @Autowired
    private UserRepository userRepo;

    @GetMapping
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
}
