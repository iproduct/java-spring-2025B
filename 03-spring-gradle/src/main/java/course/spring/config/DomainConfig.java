package course.spring.config;

import course.spring.dao.IdGenerator;
import course.spring.dao.UserRepository;
import course.spring.dao.impl.LongIdGenerator;
import course.spring.dao.impl.UserRepositoryInMemory;
import course.spring.domain.UserService;
import course.spring.domain.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

@Configuration
@PropertySource("classpath:domain.properties")
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
    public UserRepository defaultUserRepository(IdGenerator idGenerator){
        return new UserRepositoryInMemory(idGenerator);
    }

    @Bean
    public UserService defaultUserService(){
        return new UserServiceImpl(defaultUserRepository(idGenerator()));
    }
}
