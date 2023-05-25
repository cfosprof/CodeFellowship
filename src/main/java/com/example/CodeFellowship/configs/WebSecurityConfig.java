package com.example.CodeFellowship.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.HiddenHttpMethodFilter;




@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private UserDetailsService siteUserDetailsService;

    public UserDetailsService getSiteUserDetailsService() {
        return siteUserDetailsService;
    }

    public void setSiteUserDetailsService(UserDetailsService siteUserDetailsService) {
        this.siteUserDetailsService = siteUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }
    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }

    @Bean
    protected SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
        http
                .cors().disable()
                .csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/signup").permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/css/**").permitAll()
                        .requestMatchers("/users/**").authenticated()
                        .requestMatchers("/myprofile").authenticated()
                        .anyRequest().authenticated()
                )
                .formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/myprofile", true)

                .and()
                .logout()
                .logoutSuccessUrl("/login?logout")
                .permitAll()
                .and()
                .getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(siteUserDetailsService)
                .passwordEncoder(passwordEncoder());

        return http.build();
    }


//    @Bean
//    protected SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
//        http
//                .cors().disable()
//                .csrf().disable()
//                .authorizeHttpRequests((auth) -> auth
//                        .requestMatchers("/").permitAll()
//                        .requestMatchers("/signup").permitAll()
//                        .requestMatchers("/login").permitAll()
//                        .requestMatchers("/css/**").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .formLogin()
//                .loginPage("/login").permitAll()
//                .defaultSuccessUrl("/", true)
//                .and()
//                .logout()
//                .logoutSuccessUrl("/Login?logout")
//                .permitAll()
//                .and()
//                .getSharedObject(AuthenticationManagerBuilder.class)
//                .userDetailsService(siteUserDetailsService)
//                .passwordEncoder(passwordEncoder());
//
//        return http.build();
//    }
}