package com.nhd.management.configs;

import java.io.IOException;
import org.slf4j.Logger;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
  private static final Logger LOGGER =
      org.slf4j.LoggerFactory.getLogger(AuthenticationEntryPointJwt.class);

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response,
      AccessDeniedException accessDeniedException) throws IOException, ServletException {
    LOGGER.error("Unauthorized error: {}", accessDeniedException.getMessage());
    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Error: Access denied");
  }

}
