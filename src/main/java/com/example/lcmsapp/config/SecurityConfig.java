//package com.example.lcmsapp.config;
//
//import com.example.lcmsapp.service.AuthService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true, securedEnabled = true)
//@RequiredArgsConstructor
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//
//    private final AuthService authService;
//
////role based
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        //bazadan emas vaqtinchalik malumotni qo'shib turish
////        auth.inMemoryAuthentication()
////                .withUser("admin").password(passwordEncoder().encode("123")).roles("ADMIN")
////                .and()
////                .withUser("user").password(passwordEncoder().encode("user")).roles("USER")
////                .and()
////                .withUser("manager").password(passwordEncoder().encode("111")).roles("MANAGER");
////    }
//
//    //permission
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        //bazadan emas vaqtinchalik malumotni qo'shib turish
////        auth.inMemoryAuthentication()
////                .withUser("admin").password(passwordEncoder().encode("123")).authorities("WRITE_GROUP", "READ_GROUP", "DELETE_GROUP")
////                .and()
////                .withUser("user").password(passwordEncoder().encode("user")).authorities("READ_GROUP")
////                .and()
////                .withUser("manager").password(passwordEncoder().encode("111")).authorities("DELETE_GROUP");
////    }
//
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(authService).passwordEncoder(passwordEncoder()); //123 ->
//    }
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable() //cross site request forgery attack disable
//                .cors().disable() //http zaproslarni ochish
//                .authorizeRequests()
//                // .antMatchers(HttpMethod.GET,"/api/group").permitAll()
//                .antMatchers("/api/**")
//                .authenticated()
//                .and().httpBasic(); // har bir zapros kelgan ichida albattga username va parol bo'lishi kerak
//
//        //tizimga kirmagan bo'lsayam ishlayapti
//        // filter bo'lganda har zaprosdan oldin eski userni unutvorish kk
////        http.addFilterBefore().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//    }
//    //metodlari bo'ladi
//
//    //parolni kodlash
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(); //defaultni
//    }
//}
