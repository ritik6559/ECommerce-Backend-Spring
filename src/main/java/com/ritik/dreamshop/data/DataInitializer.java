package com.ritik.dreamshop.data;

import com.ritik.dreamshop.model.user.User;
import com.ritik.dreamshop.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationListener<ApplicationReadyEvent> {
    private final UserRepository userRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        createDefaultUserIfNotExists();
    }

    public void createDefaultUserIfNotExists(){
        for( int i = 0;i <= 5;i++ ){
            String defaultEmail = "user" + i + "@gmail.com";
            if( userRepository.existsByEmail(defaultEmail) ){
                continue;
            }

            User user = new User();
            user.setEmail(defaultEmail);
            user.setFirstName("The user");
            user.setLastName("User" + i);
            user.setPassword("123456");
            userRepository.save(user);
            System.out.println("Default vet user " + i + " created successfully.");
        }
    }
}
