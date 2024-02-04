package com.nhd.management.services.jwt;

import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import io.jsonwebtoken.Claims;

public interface IJwtService {
  String extractUserName(String token);

  <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

  String generateToken(UserDetails userDetails);

  String generateRefreshToken(UserDetails userDetails);

  boolean isTokenValid(String token, UserDetails userDetails);
  
  boolean validateJwtToken(String token);
}
