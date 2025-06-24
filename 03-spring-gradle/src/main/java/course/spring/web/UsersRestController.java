package course.spring.web;

import course.spring.dao.UserRepository;
import course.spring.domain.UserService;
import course.spring.dto.ErrorResponse;
import course.spring.exception.InvalidEntityDataException;
import course.spring.exception.NonexistingEntityException;
import course.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersRestController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
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

    @GetMapping("count")
    public long getUsersCount() {
        return userService.getUsersCount();
    }

    @GetMapping("{id:\\d+}")
    public User getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> addUser(@RequestBody User user) {
        var newUser = userService.addUser(user);
        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequestUri().pathSegment("{id}")
                        .buildAndExpand(newUser.getId()).toUri()
        ).body(newUser);
    }

    @PutMapping("{id}")
    public User updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        if(!id.equals(user.getId())) {
            throw new InvalidEntityDataException(
                    String.format("Non-matching IDs in path '%s' and in request body '%s'.", id, user.getId())
            );
        }
        return userService.updateUser(user);
    }

    @DeleteMapping("{id}")
    public User updateUser(@PathVariable("id") Long id) {
        return userService.deleteUserById(id);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleEntityNotFound(NonexistingEntityException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }

   @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleInvalidRequetData(InvalidEntityDataException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), ex.getViolations()));
    }
}
