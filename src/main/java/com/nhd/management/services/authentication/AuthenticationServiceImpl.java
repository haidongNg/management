package com.nhd.management.services.authentication;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.nhd.management.dto.requests.SigninRequest;
import com.nhd.management.dto.responses.JwtAuthenticationResponse;
import com.nhd.management.models.SecurityUser;
import com.nhd.management.models.User;
import com.nhd.management.services.jwt.IJwtService;
import com.nhd.management.services.user.IUserManagementService;
import com.nhd.management.utils.ZzCheckUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService {
  private final IJwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final IUserManagementService userManagementService;

  @Override
  public JwtAuthenticationResponse signin(SigninRequest theSignin) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(theSignin.getUsername(), theSignin.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    User user = userManagementService.findByUsername(theSignin.getUsername());
    String token = null;
    if (!ZzCheckUtils.isEmpty(user)) {
      token = jwtService.generateToken(new SecurityUser(user));
    }

    JwtAuthenticationResponse resp = new JwtAuthenticationResponse();
    resp.setToken(token);
    return resp;
  }

}
