package com.vidhi.erp_saas.controller;

import com.vidhi.erp_saas.dto.CustomerRequestDTO;
import com.vidhi.erp_saas.dto.CustomerResponseDTO;
import com.vidhi.erp_saas.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @PostMapping
    public ResponseEntity<CustomerResponseDTO> create(@RequestBody @Valid CustomerRequestDTO dto) {
        return new ResponseEntity<>(service.create(dto),  HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<CustomerResponseDTO>> getAll(
            @RequestParam int page,
            @RequestParam int size) {

        return new ResponseEntity<>(service.getAll(page, size), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> getById(@PathVariable Long id) {
        return new ResponseEntity<>(service.getById(id),  HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> update(@PathVariable Long id, @RequestBody @Valid CustomerRequestDTO dto) {
        return new ResponseEntity<>(service.updateById(id, dto),  HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>("Customer deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<CustomerResponseDTO>> search(
            @RequestParam String name) {

        return ResponseEntity.ok(service.searchByName(name));
    }
}
