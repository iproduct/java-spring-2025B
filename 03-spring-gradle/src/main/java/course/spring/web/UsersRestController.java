package course.spring.web;

import course.spring.dao.UserRepository;
import course.spring.exception.NonexistingEntityException;
import course.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

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

//    @GetMapping("{id}")
//    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
//        var result = userRepo.findById(id);
//        if(result.isPresent()) {
//            return ResponseEntity.ok(userRepo.findById(id).get());
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

    @GetMapping("{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userRepo.findById(id).orElseThrow(() -> new NonexistingEntityException(
                String.format("User with ID='%' does not exist.", id)
        ));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> addUser(@RequestBody User user) {
        var newUser = userRepo.create(user);
        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequestUri().pathSegment("{id}")
                        .buildAndExpand(newUser.getId()).toUri()
        ).body(newUser);
    }
}
