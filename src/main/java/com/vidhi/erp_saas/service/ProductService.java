package com.vidhi.erp_saas.service;

import com.vidhi.erp_saas.dto.CustomerResponseDTO;
import com.vidhi.erp_saas.dto.ProductRequestDTO;
import com.vidhi.erp_saas.dto.ProductResponseDTO;
import com.vidhi.erp_saas.entity.Customer;
import com.vidhi.erp_saas.entity.Product;
import com.vidhi.erp_saas.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepo;

    public ProductResponseDTO createProduct(ProductRequestDTO dto){

        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());

        Product saved = productRepo.save(product);

        return mapToDto(saved);
    }

    public Page<ProductResponseDTO> getAll(int page, int size) {

        Page<Product> productPage =
                productRepo.findAll(PageRequest.of(page, size));

        return productPage.map(this::mapToDto);
    }

    public ProductResponseDTO getById(Long id) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return mapToDto(product);
    }

    public ProductResponseDTO update(Long id, ProductRequestDTO dto) {

        Product product = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());

        Product updated = productRepo.save(product);

        return mapToDto(updated);
    }

    public void delete(Long id) {
        productRepo.deleteById(id);
    }

    public ProductResponseDTO mapToDto(Product product){

        ProductResponseDTO dto = new ProductResponseDTO();

        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setQuantity(product.getQuantity());
        dto.setCreatedAt(product.getCreatedAt());

        return dto;

    }
}
