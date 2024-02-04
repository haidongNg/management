package com.nhd.management.services.product;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nhd.management.dto.ProductDto;
import com.nhd.management.models.Product;
import com.nhd.management.repositories.IProductRepository;
import com.nhd.management.utils.MngCommonUtils;

@Service
public class ProductServiceImpl implements IProductService {

  @Autowired
  private IProductRepository productRepository;

  /**
   * Get product by Id
   */
  @Override
  public ProductDto findById(long id) {
    Optional<Product> result = productRepository.findById(id);
    ProductDto productDto = null;
    if (result.isPresent()) {
      Product resp = result.get();
      productDto = new ProductDto();
      productDto.setId(resp.getId());
      productDto.setName(resp.getName());
      productDto.setDescription(resp.getDescription());
      productDto.setCategory(resp.getCategory());
      productDto.setColor(resp.getCategory());
      productDto.setSize(resp.getSize());
      productDto.setPrice(resp.getPrice());
      productDto.setImageUrl(resp.getImageUrl());
      productDto.setQuantity(resp.getQuantity());
    }

    return productDto;
  }

  /**
   * Get all product
   * 
   */
  @Override
  public List<ProductDto> findAllProduct() {
    return productRepository.findAll().stream().map(theProduct -> {
      ProductDto productDto = new ProductDto();
      productDto.setName(theProduct.getName());
      productDto.setDescription(theProduct.getDescription());
      productDto.setCategory(theProduct.getCategory());
      productDto.setColor(theProduct.getColor());
      productDto.setSize(theProduct.getSize());
      productDto.setPrice(theProduct.getPrice());
      productDto.setImageUrl(theProduct.getImageUrl());
      productDto.setQuantity(theProduct.getQuantity());
      return productDto;

    }).collect(Collectors.toList());
  }

  /**
   * Create and Update
   * 
   */
  @Override
  public void save(ProductDto theProduct) {
    Product product = null;

    if (!MngCommonUtils.isEmpty(theProduct.getId())) {
      // Update Product
      Optional<Product> result = productRepository.findById(theProduct.getId());
      if (result.isPresent()) {
        product = result.get();
      }
    } else {
      // New
      product = new Product();
      product.setName(theProduct.getName());
      product.setDescription(theProduct.getDescription());
      product.setCategory(theProduct.getCategory());
      product.setColor(theProduct.getColor());
      product.setSize(theProduct.getSize());
      product.setPrice(theProduct.getPrice());
      product.setImageUrl(product.getImageUrl());
      product.setQuantity(product.getQuantity());
    }
    // Save
    productRepository.save(product);
  }

  /**
   * Delete product by Id
   * 
   */
  @Override
  public void deleteById(long id) {
    productRepository.deleteById(id);
  }

}
