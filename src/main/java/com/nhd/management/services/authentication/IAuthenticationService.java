package com.nhd.management.services.authentication;

import com.nhd.management.dto.requests.SigninRequest;
import com.nhd.management.dto.responses.JwtAuthenticationResponse;

public interface IAuthenticationService {
  JwtAuthenticationResponse signin(SigninRequest theSignin);
}
