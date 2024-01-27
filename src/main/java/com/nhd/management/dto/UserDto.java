package com.nhd.management.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDto extends ZzBaseDto {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * 
   */
  private int id;
  
  /**
   * 
   */
  private String fullName;
  
  /**
   * 
   */
  private String email;

  /**
   * 
   */
  private String username;

  /**
   * 
   */
  private String password;

  /**
   * 
   */
  private String roles;
}
