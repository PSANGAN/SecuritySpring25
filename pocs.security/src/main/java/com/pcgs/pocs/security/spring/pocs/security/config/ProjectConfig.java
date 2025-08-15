package com.pcgs.pocs.security.spring.pocs.security.config;

import com.pcgs.pocs.security.spring.pocs.security.services.InMemoryUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.pcgs.pocs.security.spring.pocs.security.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class ProjectConfig {

    @Bean
    public UserDetailsService userDetailsService() {

        //<>
        //        var userReader = User.withUsername("user")
        //                .password("12345")
        //                .roles("read")
        //                .build();
        //
        //         return new InMemoryUserDetailsManager(userReader);
        //

        UserDetails u = new User("john", "12345", "read");
        List<UserDetails> users = List.of(u);
        return new InMemoryUserDetailsService(users);
    }

//    @Bean
//    @SuppressWarnings("deprecation")
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("noop", NoOpPasswordEncoder.getInstance());
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        encoders.put("scrypt", new SCryptPasswordEncoder(16384, 8, 1, 32, 64));
        return new DelegatingPasswordEncoder("bcrypt", encoders);
    }
}
