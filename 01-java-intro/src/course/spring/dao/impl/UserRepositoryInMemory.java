package course.spring.dao.impl;

import course.spring.dao.IdGenerator;
import course.spring.dao.UserRepository;
import course.spring.model.User;

import java.util.Optional;

public class UserRepositoryInMemory extends RepositoryInMemory<Long, User> implements UserRepository {
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
