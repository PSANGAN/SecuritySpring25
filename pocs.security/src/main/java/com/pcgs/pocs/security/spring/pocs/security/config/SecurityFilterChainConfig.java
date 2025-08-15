package com.pcgs.pocs.security.spring.pocs.security.config;

import com.pcgs.pocs.security.spring.pocs.security.component.CustomAuthenticationProvider;
import com.pcgs.pocs.security.spring.pocs.security.filters.RequestValidationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;

@Configuration
public class SecurityFilterChainConfig {

    private final CustomAuthenticationProvider authenticationProvider;

    public SecurityFilterChainConfig(
            CustomAuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

            http.httpBasic(Customizer.withDefaults());

            http.addFilterBefore( new RequestValidationFilter(), BasicAuthenticationFilter.class);

//        http.authenticationProvider(authenticationProvider);

            http.authorizeHttpRequests(c -> c.anyRequest().authenticated());

                return http.build();
    }
}
