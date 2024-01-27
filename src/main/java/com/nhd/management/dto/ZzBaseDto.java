package com.nhd.management.dto;

import java.io.Serializable;

public abstract class ZzBaseDto implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  private int count = 0;

}
