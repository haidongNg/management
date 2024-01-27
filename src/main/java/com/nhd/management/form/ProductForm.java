package com.nhd.management.form;

import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductForm extends ZzBaseForm {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private long id;
  
  /**
   * Name of product
   */
  @NotNull(message = "is required")
  private String name;
  
  /*
   * Description of product
   */
  @NotNull(message = "is required")
  private String description;
  
  
  private String color;
  
  private String size;
  
  @NotNull(message = "is required")
  private BigDecimal price;
  
  @NotNull(message = "is required")
  private String category;
  
  @NotNull(message = "is required")
  private String imageUrl;
  
  @NotNull(message = "is required")
  private int quantity;
}
