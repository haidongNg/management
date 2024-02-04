package com.nhd.management.dto.responses;

import java.io.Serializable;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ZzGlobalSuccessResponse implements Serializable {
  /**
  * 
  */
  private static final long serialVersionUID = 1L;
  
  
  private int count;
  private Map<String, Object> data;

}
