package course.spring.dao.impl;

import course.spring.dao.UserRepository;
import course.spring.model.Role;
import course.spring.model.User;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository("mockUserRepo")
public class UserRepositoryMockImpl implements UserRepository {
    private List<User> users = List.of(
            new User(1L, "User", "One", LocalDate.now(), "user1", "user1", Role.READER, "user1@gmail.com"),
            new User(2L, "User", "Two", LocalDate.now(), "user2", "user2",  Role.AUTHOR, "user2@gmail.com"),
            new User(3L, "User", "Three", LocalDate.now(), "user3", "user3", Role.AUTHOR, "user3@gmail.com")
    );

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public User create(User entity) {
        return null;
    }

    @Override
    public Optional<User> update(User entity) {
        return Optional.empty();
    }

    @Override
    public Optional<User> deleteById(Long id) {
        return Optional.empty();
    }

    @Override
    public long count() {
        return 0;
    }
}
