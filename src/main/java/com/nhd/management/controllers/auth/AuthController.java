package com.nhd.management.controllers.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nhd.management.dto.requests.SigninRequest;
import com.nhd.management.dto.responses.JwtAuthenticationResponse;
import com.nhd.management.services.authentication.IAuthenticationService;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200",  maxAge = 3600, allowCredentials="true")
@RequestMapping("/v1/auth")
public class AuthController {
  @Autowired
  private IAuthenticationService authenticationService;

  @GetMapping("/login")
  public String login() {
    return "auth/login.html";
  }

  @PostMapping("/login")
  public ResponseEntity<JwtAuthenticationResponse> authenticateUser(@Valid @RequestBody SigninRequest theSigninRequest) {
    JwtAuthenticationResponse resp = authenticationService.signin(theSigninRequest);
    return new ResponseEntity<JwtAuthenticationResponse>(resp, HttpStatus.OK);
  }
}
