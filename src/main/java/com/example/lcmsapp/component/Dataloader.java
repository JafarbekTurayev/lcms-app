package com.example.lcmsapp.component;

import com.example.lcmsapp.entity.Role;
import com.example.lcmsapp.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Dataloader implements CommandLineRunner {
    private final RoleRepository roleRepository;

    @Value("${spring.sql.init.mode}")
    private String mode;

    @Override
    public void run(String... args) throws Exception {
        if (mode.equals("always")) {
            roleRepository.save(new Role(1l, "ADMIN"));
            roleRepository.save(new Role(2l, "USER"));
            roleRepository.save(new Role(3l, "MENTOR"));
            roleRepository.save(new Role(4l, "MODERATOR"));
        }
    }
}
