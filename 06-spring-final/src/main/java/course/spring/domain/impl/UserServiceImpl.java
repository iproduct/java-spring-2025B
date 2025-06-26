package course.spring.domain.impl;

import course.spring.dao.UserRepository;
import course.spring.domain.UserService;
import course.spring.exception.NonexistingEntityException;
import course.spring.model.User;
import jakarta.annotation.PostConstruct;
import lombok.extern.java.Log;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Log
public class UserServiceImpl implements UserService, BeanNameAware, ApplicationContextAware {
    private UserRepository userRepo;
    private String beanName;
    private ApplicationContext ctx;

    @Autowired
    public static UserService createUserService(UserRepository userRepo) {
        return new UserServiceImpl(userRepo);
    };

    @Autowired
    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public void setBeanName(String name) {
        beanName = name;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }

    @PostConstruct
    public void afterInit() throws Exception {
        log.info(String.format("!!!!! Bean '%s' created.", beanName));
//        var repo = ctx.getBean(UserRepository.class);
//        log.info("!!!!!!!!!!!!! User repository: " + repo.toString());
    }

    @Override
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
    public User getUserByUsername(String username) {
        return userRepo.findByUsername(username).orElseThrow(() -> new NonexistingEntityException(
                String.format("User with username '%s' does not exist.", username)
        ));
    }

    @Override
    public User addUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public User updateUser(User user) {
        getUserById(user.getId());
        return userRepo.save(user);
    }

    @Override
    public User deleteUserById(Long id) {
        var old = getUserById(id);
        userRepo.deleteById(id);
        return old;
    }

    @Override
    @Transactional(readOnly = true)
    public long getUsersCount() {
        return userRepo.count();
    }
}
