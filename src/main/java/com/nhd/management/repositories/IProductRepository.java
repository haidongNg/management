package com.nhd.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.nhd.management.models.Product;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {

}
