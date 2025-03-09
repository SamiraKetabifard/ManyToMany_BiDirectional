package com.example.relational_manytomany.service;

import com.example.relational_manytomany.model.Employee;
import org.springframework.data.domain.Sort;
import java.util.List;

public interface EmployeeService {

    void addEmployee(Employee employee);
    List<Employee> findAll(Sort sort);
    Employee getEmployeeById(Long id);
    Employee updateEmployee(Employee employee,Long id);
    void deleteEmployee(Long id);
}
