package com.nhd.management.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginDto {
  /**
   * User name
   */
  private String username;
  
  /**
   * Password
   */
  private String password;
}
