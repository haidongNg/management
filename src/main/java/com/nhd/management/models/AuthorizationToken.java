package com.nhd.management.models;

import java.io.Serializable;
import com.nhd.management.enums.TokenType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sys_authorization_token")
public class AuthorizationToken implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;


  @Column(name = "token")
  private String token;
  
  @Column(name = "refresh_token")
  private String refreshToken;
  
  @Enumerated(EnumType.STRING)
  private TokenType tokenType = TokenType.BEARER;
  
  @Column(name="revoked")
  private boolean revoked;
  
  @Column(name ="expired")
  private boolean expired;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;
}
