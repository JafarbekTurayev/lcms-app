package com.example.lcmsapp.component;

import com.example.lcmsapp.entity.Role;
import com.example.lcmsapp.entity.User;
import com.example.lcmsapp.repository.RoleRepository;
import com.example.lcmsapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

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
            Role admin = roleRepository.save(new Role(1l, "ADMIN"));
            Role user = roleRepository.save(new Role(2l, "USER"));
            Role mentor = roleRepository.save(new Role(3l, "MENTOR"));
            Role moderator = roleRepository.save(new Role(4l, "MODERATOR"));

            userRepository.save(new User(Set.of(admin, mentor), "TJU", "+998912455897", passwordEncoder.encode("admin"),true));
            userRepository.save(new User(Set.of(user), "AAA", "+998901112233", passwordEncoder.encode("123"),true));
        }
    }
}
