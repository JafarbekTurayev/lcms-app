package com.example.lcmsapp.service;

import com.example.lcmsapp.dto.ApiResponse;
import com.example.lcmsapp.dto.RegisterDto;
import com.example.lcmsapp.entity.User;
import com.example.lcmsapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
    @Value("${spring.mail.username}")
    String sender;
    private final UserRepository userRepository;
    private final JavaMailSender javaMailSender;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(userRepository.findByPhone(username));
        return userRepository.findByPhone(username).orElse(null);
    }

    public ApiResponse<?> register(RegisterDto registerDto) {
        User user = new User();
        user.setEnabled(false);
        user.setFullName(registerDto.getFullName());
        user.setPassword(registerDto.getPassword());
        user.setEmail(registerDto.getEmail());

        //email xabar jo'natish kerak UUID.randomUUID()
        //6-xonali
        String code = String.valueOf(Math.random() * 899999 + 100000);

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setSubject("Tasdiqlash kodi : " + code);
        simpleMailMessage.setText("Salom");
        javaMailSender.send(simpleMailMessage);

        user.setCode(code);
        userRepository.save(user);
        return ApiResponse.builder().message("Tasdiqlash kodi yuborildi").success(true).build();
    }

    public ApiResponse<?> verify(String email, String ketmon) {
        Optional<User> byEmailAndCode = userRepository.findByEmailAndCode(email, ketmon);

        if (byEmailAndCode.isPresent()) {
            User user = byEmailAndCode.get();
            user.setEnabled(true);
        }
        return ApiResponse.builder().message("Tasdiqlandi").success(true).build();
    }
}
