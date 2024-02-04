package com.nhd.management.dto.requests;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SigninRequest {
  private String username;
  private String password;
}
