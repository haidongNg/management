package com.nhd.management.services.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import javax.crypto.SecretKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServiceImpl implements IJwtService {
  private static final Logger LOGGER = LoggerFactory.getLogger(JwtServiceImpl.class);
  private static final SecretKey SECRECT = Jwts.SIG.HS256.key().build();

  @Override
  public String extractUserName(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  @Override
  public String generateToken(UserDetails userDetails) {
    return buildToken(new HashMap<>(), userDetails);
  }

  @Override
  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String userName = extractUserName(token);
    return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
  }
  
  @Override
  public <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
    final Claims claims = extractAllClaims(token);
    return claimsResolvers.apply(claims);
  }
  
  @Override
  public String generateRefreshToken(UserDetails userDetails) {
    return buildToken(new HashMap<>(), userDetails);
  }
 
  @Override
  public boolean validateJwtToken(String token) {
    try {
      Claims claims = Jwts.parser().verifyWith(getSingKey()).build().parseSignedClaims(token).getPayload();
      if (claims != null) {
        return true;
      }
    } catch (MalformedJwtException e) {
      LOGGER.error("Invalid JWT token: {}", e.getMessage());
    } catch (ExpiredJwtException e) {
      LOGGER.error("JWT token is expired: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
      LOGGER.error("JWT token is unsupported: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      LOGGER.error("JWT claims string is empty: {}", e.getMessage());
    }
    return false;
  }
  
  private String buildToken(Map<String, Object> extraClaims, UserDetails userDetails) {
    return Jwts.builder().claims(extraClaims).subject(userDetails.getUsername())
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
        .signWith(getSingKey())
        .compact();
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parser().verifyWith(getSingKey()).build().parseSignedClaims(token).getPayload();

  }

  private SecretKey getSingKey() {
    String secretString = Encoders.BASE64.encode(SECRECT.getEncoded());
    byte[] keyBytes = Decoders.BASE64.decode(secretString);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
