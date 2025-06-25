package course.spring.dao.impl;

import course.spring.dao.IdGenerator;
import course.spring.dao.RepoFactory;
import course.spring.dao.UserRepository;

public class RepoFactoryInMemory implements RepoFactory {

    @Override
    public UserRepository createUserRepository(IdGenerator idGenerator) {
        return new UserRepositoryInMemory(idGenerator);
    }
}
