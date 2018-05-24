package bookStore;

import bookStore.repository.*;
import bookStore.service.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Bean(name = "UserService")
    public UserService userService(UserRepository repository) {
        return new CachingUserService(new UserServiceImpl(repository));
    }


    @Bean(name = "FriendService")
    public FriendService friendService(FriendRepository repository){
        return new CachingFriendService(new FriendServiceImpl(repository));
    }

}
