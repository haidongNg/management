package com.nhd.management.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.nhd.management.services.user.IUserManagementService;

@Configuration
public class ApplicationConfig {

  @Bean
  BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

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

}
