package com.nhd.management.dto.responses;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ZzSystemException extends RuntimeException {
  /**
  * 
  */
  private static final long serialVersionUID = 1L;
  
  public ZzSystemException(String theMessage) {
   super(theMessage);
  }

}
