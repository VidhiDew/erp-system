package com.vidhi.erp_saas.repository;

import com.vidhi.erp_saas.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>{
}
