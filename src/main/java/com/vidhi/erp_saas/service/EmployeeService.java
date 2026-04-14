package com.vidhi.erp_saas.service;

import com.vidhi.erp_saas.entity.Employee;
import com.vidhi.erp_saas.exception.ResourceNotFoundException;
import com.vidhi.erp_saas.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepo;

    public Employee save(Employee employee) {
        return employeeRepo.save(employee);
    }


    public Employee getEmployeeById(Long id) {

        Employee employee = employeeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));

        return employee;
    }

    public List<Employee> getAllEmployee(){
        return employeeRepo.findAll();

    }
    public void deleteEmployee(Long id){
        employeeRepo.deleteById(id);
    }

    public Employee updateEmployee(Employee emp, Long id){

        Employee existingEmployee = employeeRepo.findById(id).orElse(emp);
        existingEmployee.setName(emp.getName());
        existingEmployee.setEmail(emp.getEmail());
        existingEmployee.setSalary(emp.getSalary());
        existingEmployee.setDepartment(emp.getDepartment());

        return employeeRepo.save(existingEmployee);
    }
}
