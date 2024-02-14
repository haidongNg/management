package com.nhd.management.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
@EnableWebSecurity()
@EnableMethodSecurity()
public class SecurityConfig {
  @Autowired
  private JwtAuthenticationFilter jwtAuthenticationFilter;

  @Autowired
  private AuthenticationProvider authenticationProvider;

  @Autowired
  private CustomAccessDeniedHandler accessDeniedHandler;

  @Autowired
  private AuthenticationEntryPointJwt unauthorizedHandler;

  @Autowired
  private LogoutHandler logoutHandler;

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity https) throws Exception {
    https.csrf(configurer -> configurer.disable())
        .exceptionHandling(configurer -> configurer.accessDeniedHandler(accessDeniedHandler)
            .authenticationEntryPoint(unauthorizedHandler))
        .authorizeHttpRequests(
            configurer -> configurer.requestMatchers(HttpMethod.POST, "/v1/auth/*").permitAll()
                .anyRequest().authenticated())
        .sessionManagement(
            configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .headers(configurer -> configurer.frameOptions(
            config -> config.sameOrigin().httpStrictTransportSecurity(conf -> conf.disable())))
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        .logout(configurer -> configurer.logoutUrl("/v1/auth/logout")
            .addLogoutHandler(logoutHandler).logoutSuccessHandler(
                (request, response, authentication) -> SecurityContextHolder.clearContext()));
    return https.build();
  }
}
