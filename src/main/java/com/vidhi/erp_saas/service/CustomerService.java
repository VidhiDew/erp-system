package com.vidhi.erp_saas.service;

import com.vidhi.erp_saas.dto.CustomerRequestDTO;
import com.vidhi.erp_saas.dto.CustomerResponseDTO;
import com.vidhi.erp_saas.entity.Customer;
import com.vidhi.erp_saas.exception.ResourceAlreadyExistsException;
import com.vidhi.erp_saas.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public CustomerResponseDTO create(CustomerRequestDTO dto) {

        if (repository.findByEmail(dto.getEmail()).isPresent()) {
            throw new ResourceAlreadyExistsException("Email already exists");
        }

        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());
        customer.setAddress(dto.getAddress());

        Customer saved = repository.save(customer);

        return mapToDTO(saved);
    }

    public Page<CustomerResponseDTO> getAll(int page, int size) {

        Page<Customer> customerPage =
                repository.findAll(PageRequest.of(page, size));

        return customerPage.map(this::mapToDTO);
    }

    public CustomerResponseDTO getById(Long id) {
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        return mapToDTO(customer);
    }

    public CustomerResponseDTO updateById(Long id, CustomerRequestDTO dto) {

        Customer customer = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());
        customer.setAddress(dto.getAddress());

        Customer updated = repository.save(customer);

        return mapToDTO(updated);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<CustomerResponseDTO> searchByName(String name) {

        List<Customer> customers =
                repository.findByNameContainingIgnoreCase(name);

        return customers.stream()
                .map(this::mapToDTO)
                .toList();
    }

    private CustomerResponseDTO mapToDTO(Customer customer) {
        CustomerResponseDTO dto = new CustomerResponseDTO();
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setEmail(customer.getEmail());
        dto.setPhone(customer.getPhone());
        dto.setAddress(customer.getAddress());
        return dto;
    }
}
