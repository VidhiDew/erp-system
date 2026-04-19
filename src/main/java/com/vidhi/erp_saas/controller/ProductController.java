package com.vidhi.erp_saas.controller;

import com.vidhi.erp_saas.dto.CustomerResponseDTO;
import com.vidhi.erp_saas.dto.ProductRequestDTO;
import com.vidhi.erp_saas.dto.ProductResponseDTO;
import com.vidhi.erp_saas.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDTO> saveProduct(@RequestBody @Valid ProductRequestDTO dto){
        ProductResponseDTO productDTO = productService.createProduct(dto);
        return ResponseEntity.ok(productDTO);
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponseDTO>> getAll(
            @RequestParam int page,
            @RequestParam int size) {

        return ResponseEntity.ok(productService.getAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getById(@PathVariable Long id) {
        return new ResponseEntity<>(productService.getById(id),  HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> update(
            @PathVariable Long id,
            @RequestBody ProductRequestDTO dto) {

        return ResponseEntity.ok(productService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.ok("Product deleted successfully");
    }


}
