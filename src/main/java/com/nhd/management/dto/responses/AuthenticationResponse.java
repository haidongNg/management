package com.nhd.management.dto.responses;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AuthenticationResponse {
  private String accessToken;
  private String refreshToken;
}
