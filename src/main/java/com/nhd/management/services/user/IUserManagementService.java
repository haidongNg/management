package com.nhd.management.services.user;

import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.nhd.management.dto.UserDto;
import com.nhd.management.models.User;

public interface IUserManagementService extends UserDetailsService{
  public User findByUsername(String theUsername);
  public void register(UserDto theUserDto);
  public List<User> getAllListUser();
  public User findById(long theId);
  public void deleteById(long theId);
  public void save(UserDto theUserDto);
  boolean isTokenValid(String token);
}
