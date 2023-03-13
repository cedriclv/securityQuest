package com.wildcodeschool.mySecurityQuest.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        String championPassword = passwordEncoder.encode("champion");
        UserDetails champion = User.withUsername("champion")
                .password(championPassword)
                .roles("CHAMPION")
                .build();

        String directorPassword = passwordEncoder.encode("director");
        UserDetails director = User.withUsername("director")
                .password(directorPassword)
                .roles("DIRECTOR")
                .build();

        return new InMemoryUserDetailsManager(champion, director);
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/").permitAll();
                    auth.requestMatchers("/avengers/assemble").hasAnyRole("CHAMPION", "DIRECTOR");
                    auth.requestMatchers("/secret-bases").hasRole("DIRECTOR");
                    auth.anyRequest().authenticated();
                })
                .formLogin(Customizer.withDefaults())
                .build();
    }
}