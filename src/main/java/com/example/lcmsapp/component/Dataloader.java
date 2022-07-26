package com.example.lcmsapp.component;

import com.example.lcmsapp.entity.Role;
import com.example.lcmsapp.entity.User;
import com.example.lcmsapp.repository.RoleRepository;
import com.example.lcmsapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class Dataloader implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Value("${spring.sql.init.mode}")
    private String mode;

    @Override
    public void run(String... args) throws Exception {
        if (mode.equals("always")) {
            Role admin = roleRepository.save(new Role(1l, "ADMIN",true));
            Role user = roleRepository.save(new Role(2l, "USER",true));
            Role mentor = roleRepository.save(new Role(3l, "MENTOR",true));
            Role moderator = roleRepository.save(new Role(4l, "MODERATOR",true));

            Set<Role> roles = new HashSet<>();
            roles.add(admin);
            roles.add(user);

            userRepository.save(new User("jekka",roles, "TJU", "admin", passwordEncoder.encode("123"), true));
            userRepository.save(new User("pekka",roles, "AAA", "user", passwordEncoder.encode("123"), true));
        }
    }

}
