package course.spring.domain.impl;

import course.spring.dao.UserRepository;
import course.spring.domain.UserService;
import course.spring.exception.NonexistingEntityException;
import course.spring.model.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService, BeanNameAware, ApplicationContextAware {
    private UserRepository userRepo;

    @Autowired
    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public void setBeanName(String name) {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new NonexistingEntityException(
                String.format("User with ID='%s' does not exist.", id)
        ));
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepo.findByUsername(username).orElseThrow(() -> new NonexistingEntityException(
                String.format("User with username '%s' does not exist.", username)
        ));
    }

    @Override
    public User addUser(User user) {
        return userRepo.create(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepo.update(user).orElseThrow(() -> new NonexistingEntityException(
                String.format("Can not update non-existing user '%s' with ID='%d'", user.getUsername(), user.getId())
        ));
    }

    @Override
    public User deleteUserById(Long id) {
        return userRepo.deleteById(id).orElseThrow(() -> new NonexistingEntityException(
                String.format("Can not delete non-existing user with ID='%d'", id)
        ));
    }

    @Override
    public long getUsersCount() {
        return userRepo.count();
    }

}
