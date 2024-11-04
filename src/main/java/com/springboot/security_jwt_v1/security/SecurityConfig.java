package com.springboot.security_jwt_v1.security;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf->csrf.disable())
                //.httpBasic(Customizer.withDefaults())
                .sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize-> authorize
                        //For Role class
                        .requestMatchers(HttpMethod.GET,"/api/roles").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/api/roles-by-id/*").hasRole("ADMIN")
                        //For User class
                        .requestMatchers(HttpMethod.POST,"/auth/users-register").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/auth/users-login").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/auth/users/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/auth/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/auth/users-by-id/*").hasRole("ADMIN")
                        //For Products class
                        .requestMatchers(HttpMethod.GET,"/api/products").hasAnyRole("USER","ADMIN")
                        .requestMatchers(HttpMethod.GET,"/api/products-by-id/*").hasAnyRole("USER","ADMIN")
                        .requestMatchers(HttpMethod.POST,"/api/products").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/api/products/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/api/products/*").hasRole("ADMIN")
                        .anyRequest().denyAll())
                .addFilterBefore(jwtAuthenticationFilter,UsernamePasswordAuthenticationFilter.class)//Or UsernamePasswordAuthenticationFilter.class //BasicAuthenticationFilter.class
                .build();
    }

}
