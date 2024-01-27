package com.nhd.management.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.nhd.management.services.user.IUserManagementService;

@Configuration
@EnableWebSecurity()
public class SecurityConfig {

  /**
   * AuthenticationProvider bean definition
   * 
   * @param userManagementService
   * @return
   */
  @Bean
  DaoAuthenticationProvider authenticationProvider(IUserManagementService userManagementService) {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    // set the custom user details service
    authProvider.setUserDetailsService(userManagementService);
    // set the password encoder - bcrypt
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }

  @Bean
  AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration)
      throws Exception {
    return authConfiguration.getAuthenticationManager();
  }

  @Bean
  WebSecurityCustomizer webSecurityCustomizer() {
      return (web) -> web.ignoring()
      // Spring Security should completely ignore URLs starting with /resources/
              .requestMatchers("/css/**").requestMatchers("/js/**");
  }

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity https) throws Exception {
    https
        .authorizeHttpRequests(configurer -> configurer.requestMatchers("/users/**").hasAnyAuthority("ADMIN","MANAGER").anyRequest().authenticated())
        .formLogin(form -> form.loginPage("/auth/login").loginProcessingUrl("/authenticateTheUser")
            .defaultSuccessUrl("/", true).permitAll())
        .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/auth/login")
            .deleteCookies("JSESSIONID").permitAll())
        .exceptionHandling(configurer -> configurer.accessDeniedPage("/access-denied"));
    return https.build();
  }

  @Bean
  BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
