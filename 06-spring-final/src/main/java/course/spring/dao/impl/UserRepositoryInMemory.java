package course.spring.dao.impl;

import course.spring.dao.IdGenerator;
import course.spring.dao.UserRepository;
import course.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Scope("singleton")
public class UserRepositoryInMemory extends RepositoryInMemory<Long, User> implements UserRepository {

    private static UserRepository theInstance = new UserRepositoryInMemory(new LongIdGenerator());

    public static UserRepository getInstance() {
        return theInstance;
    }

//    @Autowired
    public UserRepositoryInMemory(IdGenerator<Long> idGenerator) {
        super(idGenerator);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        var allUsers = findAll();
        for(User u : allUsers){
            if(u.getUsername().equals(username)) return Optional.of(u);
        }
        return Optional.empty();
    }
}
