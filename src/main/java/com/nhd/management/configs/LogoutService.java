package com.nhd.management.configs;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;
import com.nhd.management.models.AuthorizationToken;
import com.nhd.management.repositories.IAuthorizationTokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class LogoutService implements LogoutHandler {

  @Autowired
  private IAuthorizationTokenRepository authorizationTokenRepository;

  @Override
  public void logout(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) {
    final String authHeader = request.getHeader("Authorization");
    final String token;
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      return;
    }
    token = authHeader.substring(7);
    Optional<AuthorizationToken> result = authorizationTokenRepository.findByToken(token);
    if (result.isPresent()) {
      AuthorizationToken authToken = result.get();
      authToken.setExpired(true);
      authToken.setRevoked(true);
      authorizationTokenRepository.save(authToken);
      SecurityContextHolder.clearContext();
    }
  }

}
