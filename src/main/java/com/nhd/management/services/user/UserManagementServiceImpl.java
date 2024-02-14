package com.nhd.management.services.user;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.nhd.management.dto.UserDto;
import com.nhd.management.models.AuthorizationToken;
import com.nhd.management.models.SecurityUser;
import com.nhd.management.models.User;
import com.nhd.management.repositories.IAuthorizationTokenRepository;
import com.nhd.management.repositories.IUserRepository;
import com.nhd.management.utils.MngCommonUtils;
import com.nhd.management.utils.ZzCheckUtils;

@Service
public class UserManagementServiceImpl implements IUserManagementService {

  @Autowired
  private IUserRepository userRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private IAuthorizationTokenRepository authorizationTokenRepository;


  @Override
  public User findByUsername(String theUsername) {
    Optional<User> result = userRepository.findByUsername(theUsername);
    User user = null;
    if (result.isPresent()) {
      user = result.get();
    }
    return user;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByUsername(username).map(SecurityUser::new)
        .orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));
  }

  /**
   * Register User
   */
  @Override
  public void register(UserDto userDto) {
    User newUser = new User();
    newUser.setFullName(userDto.getFullName());
    newUser.setEmail(userDto.getEmail());
    newUser.setUsername(userDto.getUsername());
    newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
    if (!ZzCheckUtils.isEmpty(userDto.getRoles())) {
      newUser.setRoles(userDto.getRoles());
    }
    newUser.setRoles(userDto.getRoles());
    userRepository.save(newUser);
  }

  /**
   * Save User
   */
  @Override
  public void save(UserDto theUserDto) {
    User newUser = null;
    if (!MngCommonUtils.isEmpty(theUserDto.getId())) {
      // Update User
      newUser = findById(theUserDto.getId());
    }

    if (MngCommonUtils.isEmpty(newUser)) {
      // Create user
      newUser = new User();
      newUser.setPassword(passwordEncoder.encode(theUserDto.getPassword()));
    }
    newUser.setFullName(theUserDto.getFullName());
    newUser.setEmail(theUserDto.getEmail());
    newUser.setUsername(theUserDto.getUsername());
    userRepository.save(newUser);
  }

  /**
   * Get all list user
   */
  @Override
  public List<User> getAllListUser() {
    return userRepository.findAll();
  }

  /**
   * Find user by id
   */
  @Override
  public User findById(long theId) throws UsernameNotFoundException {
    Optional<User> result = userRepository.findById(theId);
    User user = null;
    if (result.isPresent()) {
      user = result.get();
    } else {
      throw new UsernameNotFoundException("User not found");
    }
    return user;
  }

  /**
   * Delete user by id
   */
  @Override
  public void deleteById(long theId) {
    userRepository.deleteById(theId);
  }
  
  @Override
  public boolean isTokenValid(String token) {
   Optional<AuthorizationToken> result = authorizationTokenRepository.findByToken(token);
   if (result.isPresent()) {
     return !result.get().isExpired() && !result.get().isRevoked();
   }
    return false;
  }
}
