package com.nhd.management.services.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.nhd.management.dto.requests.AuthenticationRequest;
import com.nhd.management.dto.responses.AuthenticationResponse;
import com.nhd.management.models.AuthorizationToken;
import com.nhd.management.models.SecurityUser;
import com.nhd.management.models.User;
import com.nhd.management.repositories.IAuthorizationTokenRepository;
import com.nhd.management.services.jwt.IJwtService;
import com.nhd.management.services.user.IUserManagementService;
import com.nhd.management.utils.ZzCheckUtils;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {
  @Autowired
  private IJwtService jwtService;
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private IUserManagementService userManagementService;

  @Autowired
  private IAuthorizationTokenRepository authorizationTokenRepository;

  @Override
  public AuthenticationResponse signin(AuthenticationRequest theSignin) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(theSignin.getUsername(), theSignin.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    User user = userManagementService.findByUsername(theSignin.getUsername());
    AuthenticationResponse resp = new AuthenticationResponse();
    if (!ZzCheckUtils.isEmpty(user)) {
      SecurityUser securityUser = new SecurityUser(user);
      resp.setAccessToken(jwtService.generateToken(securityUser));
      resp.setRefreshToken(jwtService.generateRefreshToken(securityUser));
      createAuthorizationToken(user, resp.getAccessToken(), resp.getRefreshToken());
    }
    return resp;
  }

  private void createAuthorizationToken(User user, String token, String refreshToken) {
    if (ZzCheckUtils.isEmpty(user.getAuthorizationToken())) {
      AuthorizationToken authToken = new AuthorizationToken();
      authToken.setToken(token);
      authToken.setRefreshToken(refreshToken);
      authToken.setExpired(false);
      authToken.setRevoked(false);
      authToken.setUser(user);
      authorizationTokenRepository.save(authToken);
    } else if (user.getAuthorizationToken().isExpired()) {
      AuthorizationToken oldToken = user.getAuthorizationToken();
      oldToken.setUser(null);
      authorizationTokenRepository.save(oldToken);
    }
  }
}
