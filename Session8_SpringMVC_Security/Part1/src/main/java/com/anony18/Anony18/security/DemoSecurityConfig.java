package com.anony18.Anony18.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {

    // add  support for JDBC /// no more hardcore users
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){

        JdbcUserDetailsManager theUserDetailManager = new JdbcUserDetailsManager(dataSource);

        theUserDetailManager
                .setUsersByUsernameQuery("select user_id, pw, active from members where user_id=?");
        theUserDetailManager
                .setAuthoritiesByUsernameQuery("select user_id, role from roles where user_id=?");

        return theUserDetailManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configuer ->
                configuer
                        .requestMatchers("/").hasRole("EMPLOYEE")
                        .requestMatchers("/leaders/**").hasRole("MANAGER")
                        .requestMatchers("/systems/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
        ).exceptionHandling(configurer ->
                        configurer
                                .accessDeniedPage("/access-denied")
        ).formLogin(form->
                form
                        .loginPage("/showMyLoginPage")
                        .loginProcessingUrl("/authenticateTheUser")
                        .permitAll()
        ).logout(logout -> logout.permitAll());
        return http.build();
    }
}
