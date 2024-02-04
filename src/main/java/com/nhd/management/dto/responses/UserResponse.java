package com.nhd.management.dto.responses;

import java.util.List;
import com.nhd.management.dto.ZzBaseDto;
import com.nhd.management.models.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserResponse extends ZzBaseDto {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private List<User> data;
}
