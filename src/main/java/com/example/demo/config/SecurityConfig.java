package com.example.demo.config;

import com.example.demo.security.JwtAuthenticationFilter;
import com.example.demo.security.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public JwtTokenProvider jwtTokenProvider() {
        return new JwtTokenProvider(
                "MySuperSecretVendorSlaKey1234567890",
                3600000L
        );
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(
            JwtTokenProvider jwtTokenProvider) {
        return new JwtAuthenticationFilter(jwtTokenProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
public SecurityFilterChain filterChain(HttpSecurity http,
                                       JwtAuthenticationFilter jwtFilter)
        throws Exception {

    http
        // ❗ Disable CSRF completely (required for Swagger + JWT)
        .csrf(csrf -> csrf.disable())

        // ❗ Stateless session (JWT-based)
        .sessionManagement(sm ->
                sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

        // ❗ Authorization rules
        .authorizeHttpRequests(auth -> auth
                // 🔓 AUTH endpoints MUST be public
                .requestMatchers(
                        "/auth/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/v3/api-docs/**"
                ).permitAll()

                // 🔒 Everything else requires JWT
                .anyRequest().authenticated()
        )

        // ❗ JWT filter
        .addFilterBefore(
                jwtFilter,
                UsernamePasswordAuthenticationFilter.class
        );

    return http.build();
}

}
