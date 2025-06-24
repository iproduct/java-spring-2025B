package course.spring.config;

import course.spring.dao.IdGenerator;
import course.spring.dao.UserRepository;
import course.spring.dao.impl.LongIdGenerator;
import course.spring.dao.impl.UserRepositoryInMemory;
import course.spring.dao.impl.UserRepositoryMockImpl;
import course.spring.domain.UserService;
import course.spring.domain.impl.UserServiceImpl;
import course.spring.model.User;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@PropertySource("classpath:domain.properties")
@Log
public class DomainConfig {
    @Value("${idgen.initialValue}")
    private long generatorInitialValue = 0L;

    @Bean("longIdGen")
    @Scope("prototype")
    public IdGenerator idGenerator(){
        var idGen = new LongIdGenerator();
        idGen.setInitialValue(generatorInitialValue);
        return idGen;
    }

    @Bean
    @Scope("singleton")
    @Qualifier("IN_MEMORY")
    public UserRepository defaultUserRepository(IdGenerator idGenerator){
        return new UserRepositoryInMemory(idGenerator);
    }

    @Bean
    public UserService defaultUserService(
            UserRepository[] userRepository
    ){
    log.info("!!!! NUM REPOS: " + userRepository.length);
        return new UserServiceImpl(new UserRepositoryMockImpl() {
            @Override
            public List<User> findAll() {
                return Arrays.stream(userRepository)
                        .flatMap(userRepo -> userRepo.findAll().stream())
                        .collect(Collectors.toList());
            }
        });
    }
}
