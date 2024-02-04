package com.nhd.management.configs;

import java.io.IOException;
import org.slf4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthenticationEntryPointJwt implements AuthenticationEntryPoint {
  private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(AuthenticationEntryPointJwt.class);
  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authException) throws IOException, ServletException {
    LOGGER.error("Unauthorized error: {}", authException.getMessage());
    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");
  }

}
