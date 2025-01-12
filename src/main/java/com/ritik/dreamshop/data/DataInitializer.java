package com.ritik.dreamshop.data;

import com.ritik.dreamshop.model.role.Role;
import com.ritik.dreamshop.model.user.User;
import com.ritik.dreamshop.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Transactional
@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationListener<ApplicationReadyEvent> {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Set<String> defaultRoles = Set.of("ROLE_ADMIN", "ROLE_USER");
        createDefaultRoleIfNotExits(defaultRoles);
        createDefaultUserIfNotExists();
        createDefaultAdminIfNotExists();

    }

    public void createDefaultUserIfNotExists(){
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        for( int i = 0;i <= 5;i++ ){
            String defaultEmail = "user" + i + "@gmail.com";
            if( userRepository.existsByEmail(defaultEmail) ){
                continue;
            }

            User user = new User();
            user.setEmail(defaultEmail);
            user.setFirstName("The user");
            user.setLastName("User" + i);
            user.setPassword(passwordEncoder.encode("123456"));
            user.setRoles(Set.of(userRole));
            userRepository.save(user);
            System.out.println("Default vet user " + i + " created successfully.");
        }
    }

    public void createDefaultAdminIfNotExists(){
        Role adminRole = roleRepository.findByName("ROLE_ADMIN").get();
        for( int i = 0;i <= 1;i++ ){
            String defaultEmail = "admin" + i + "@gmail.com";
            if( userRepository.existsByEmail(defaultEmail) ){
                continue;
            }

            User user = new User();
            user.setEmail(defaultEmail);
            user.setFirstName("Admin");
            user.setLastName("Admin" + i);
            user.setPassword(passwordEncoder.encode("123456"));
            user.setRoles(Set.of(adminRole));
            userRepository.save(user);
            System.out.println("Default admin user " + i + " created successfully.");
        }
    }

    private void createDefaultRoleIfNotExits(Set<String> roles){
        roles.stream()
                .filter(role -> roleRepository.findByName(role).isEmpty())
                .map(Role :: new).forEach(roleRepository::save);

    }


}
