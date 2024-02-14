package com.nhd.management.services.authentication;

import com.nhd.management.dto.requests.AuthenticationRequest;
import com.nhd.management.dto.responses.AuthenticationResponse;

public interface IAuthenticationService {
  AuthenticationResponse signin(AuthenticationRequest theSignin);
}
