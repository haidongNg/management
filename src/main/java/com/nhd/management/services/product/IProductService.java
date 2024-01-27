package com.nhd.management.services.product;

import java.util.List;
import com.nhd.management.dto.ProductDto;

public interface IProductService {
  ProductDto findById(long id);
  List<ProductDto> findAllProduct();
  void save(ProductDto theProduct);
  void deleteById(long id);
}
