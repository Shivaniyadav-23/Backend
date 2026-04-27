package com.payroll.backend.service;

import com.payroll.backend.exception.ResourceNotFoundException;
import com.payroll.backend.model.Employee;
import com.payroll.backend.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
     public Employee createEmployee(Employee employee){
        return employeeRepository.save(employee);
     }
     public List<Employee> getAllEmployee(){
        return employeeRepository.findAll();
     }
     public Employee getEmployeeById(Long id){
        return employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee not found"));
     }
     public Employee updateEmployee(Long id, Employee updatedEmployee){
        Employee existing = getEmployeeById(id);

         existing.setName(updatedEmployee.getName());
         existing.setEmail(updatedEmployee.getEmail());
         existing.setRole(updatedEmployee.getRole());
         existing.setWorkMode(updatedEmployee.getWorkMode());
         existing.setSalaryType(updatedEmployee.getSalaryType());
         existing.setSalaryAmount(updatedEmployee.getSalaryAmount());

        return employeeRepository.save(existing);
     }
}
