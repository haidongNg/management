package com.nhd.management;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.nhd.management.dto.UserDto;
import com.nhd.management.enums.RoleName;
import com.nhd.management.services.user.UserManagementServiceImpl;

//@EnableScheduling
@SpringBootApplication
public class ManagementApplication {

  public static void main(String[] args) {
    SpringApplication.run(ManagementApplication.class, args);
  }

  @Bean
  CommandLineRunner commandLineRunner(UserManagementServiceImpl service) {
    return args -> {
      UserDto newUser = new UserDto();
      newUser.setFullName("David Moyes");
      newUser.setEmail("david.moyes@abc.com");
      newUser.setUsername("david");
      newUser.setPassword("david");
      newUser.setRoles(RoleName.EMPLOYEE.name());
      service.register(newUser);
      
      UserDto newUser2 = new UserDto();
      newUser2.setFullName("Yamada Yoshinobu");
      newUser2.setEmail("yamada.yoshinobu@abc.com");
      newUser2.setUsername("yamada");
      newUser2.setPassword("yamada");
      newUser2.setRoles(RoleName.MANAGER.name());
      service.register(newUser2);
      
      UserDto newUser3 = new UserDto();
      newUser3.setFullName("Takahashi");
      newUser3.setEmail("takahashi@abc.com");
      newUser3.setUsername("takahashi");
      newUser3.setPassword("takahashi");
      newUser3.setRoles(RoleName.ADMIN.name());
      service.register(newUser3);
    };
  }
}
