package course.spring.dao;

public interface RepoFactory {
    UserRepository createUserRepository(IdGenerator idGenerator);
}
