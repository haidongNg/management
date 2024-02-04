package com.nhd.management.form;

import java.math.BigDecimal;
import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.constraints.NotEmpty;
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
  @NotEmpty(message = "is required")
  @NotNull(message = "is required")
  private String name;
  
  /*
   * Description of product
   */
  @NotEmpty(message = "is required")
  @NotNull(message = "is required")
  private String description;
  
  @NotEmpty(message = "is required")
  @NotNull(message = "is required")
  private String color;
  
  @NotEmpty(message = "is required")
  @NotNull(message = "is required")
  private String size;
  
  @NotNull(message = "is required")
  private BigDecimal price;
  
  @NotEmpty(message = "is required")
  @NotNull(message = "is required")
  private String category;
  
  @NotEmpty(message = "is required")
  @NotNull(message = "is required")
  private String imageUrl;
  
  @NotNull(message = "is required")
  private int quantity;
  
  private MultipartFile[] files;
}
