package com.nhd.management.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class ProductDto implements Serializable{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private long id;

  private String name;

  private String description;

  private String color;

  private String size;

  private String category;

  private BigDecimal price;

  private String imageUrl;
  
  private int quantity;
  
}
