package com.nhd.management.models;

import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity()
@Table(name = "product")
public class Product extends ZzBaseModel {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  
  @Column(name = "name")
  private String name;
  
  @Column(name = "description")
  private String description;
  
  @Column(name = "color")
  private String color;
  
  @Column(name = "size")
  private String size;
  
  @Column(name = "category")
  private String category;
  
  @Column(name = "price")
  private BigDecimal price;
  
  @Column(name = "image_url")
  private String imageUrl;
  
  @Column(name = "quantity")
  private int quantity;
}
