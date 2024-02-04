package com.nhd.management.controllers.product;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.nhd.management.dto.ProductDto;
import com.nhd.management.form.ProductForm;
import com.nhd.management.services.product.IProductService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/products")
public class ProductController {
  @Autowired
  private IProductService productService;

  @GetMapping("")
  public String index(Model theModel) {
    List<ProductDto> products = productService.findAllProduct();
    theModel.addAttribute("products", products);
    return "pages/products/index.html";
  }

  @GetMapping("/register")
  public String getMethodName(Model theModel) {
    ProductForm productForm = new ProductForm();
    theModel.addAttribute("productForm", productForm);
    return "pages/products/register.html";
  }

  @PostMapping("/save")
  public String postMethodName(@Valid @ModelAttribute("productForm") ProductForm theProductForm,
      BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "pages/products/register.html";
    }

    ProductDto productDto = new ProductDto();
    productDto.setId(theProductForm.getId());
    productDto.setCategory(theProductForm.getCategory());
    productDto.setColor(theProductForm.getColor());
    productDto.setSize(theProductForm.getSize());
    productDto.setName(theProductForm.getName());
    productDto.setDescription(theProductForm.getDescription());
    productDto.setQuantity(theProductForm.getQuantity());
    productDto.setImageUrl(theProductForm.getImageUrl());
    productService.save(productDto);
    return "redirect:/products";
  }

}
