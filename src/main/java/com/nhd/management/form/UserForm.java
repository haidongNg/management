package com.nhd.management.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserForm extends ZzBaseForm {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  /**
   * 
   */
  private Integer id;

  /**
   * 
   */
  @NotNull(message = "is required")
  private String fullName;

  /**
   * 
   */
  @NotNull(message = "is required")
  @Email
  private String email;

  /**
   * 
   */
  @NotNull(message = "is required")
  @Size(min = 3, max = 30)
  private String username;

  /**
   * 
   */
  @NotNull(message = "is required")
  @Size(min = 6)
  private String password;

  /**
   * 
   */
  private String roles;
}
