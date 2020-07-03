package com.example.university_register2.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/index","/test/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/v1/**").hasAuthority("PROFESOR")
                .antMatchers(HttpMethod.POST, "/api/v1/**").hasAuthority("PROFESOR")
                .antMatchers(HttpMethod.PUT, "/api/v1/**").hasAnyAuthority("PROFESOR")
                .anyRequest()
                .authenticated()
                .and()
//                .httpBasic();
                .formLogin()
                .defaultSuccessUrl("/api/v1/students", true);

    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails master = User.builder()
                .username("master")
                .password(passwordEncoder.encode("pass"))
                .authorities("PROFESOR")
                .build();

        UserDetails teacher = User.builder()
                .username("teacher")
                .password(passwordEncoder.encode("pass"))
                .authorities("PROF_TRAINNE")
                .build();

        UserDetails wilma = User.builder()
                .username("wilma")
                .password(passwordEncoder.encode("pass"))
                .authorities("M1")
                .build();

        UserDetails larry = User.builder()
                .username("larry")
                .password(passwordEncoder.encode("pass"))
                .authorities("M2")
                .build();
        UserDetails alma = User.builder()
                .username("alma")
                .password(passwordEncoder.encode("pass"))
                .authorities("C1")
                .build();

        UserDetails sheila = User.builder()
                .username("sheila")
                .password(passwordEncoder.encode("pass"))
                .authorities("P1")
                .build();


        return new InMemoryUserDetailsManager(
                master,
                teacher,
                wilma,
                larry,
                alma,
                sheila
        );


    }
}
