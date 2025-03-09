package com.example.relational_manytomany.service;

import com.example.relational_manytomany.model.Employee;
import com.example.relational_manytomany.model.Project;
import com.example.relational_manytomany.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void addEmployee(Employee employee) {
        for (Project project : employee.getProjects()) {
            project.getEmployees().add(employee);
        }
        employeeRepository.save(employee);
    }
    @Override
    public List<Employee> findAll(Sort sort){
        return employeeRepository.findAll(sort);
    }
    @Override
    public  Employee getEmployeeById(Long id){
        return employeeRepository.findById(id).orElse(null);
    }
    @Override
    public void deleteEmployee(Long id){
        employeeRepository.deleteById(id);
    }
    @Override
    public Employee updateEmployee(Employee employee,Long id){
        Employee updatedEmployee = employeeRepository.findById(id).orElse(null);
        if(updatedEmployee != null){
            updatedEmployee.setName(employee.getName());
            updatedEmployee.setProjects(employee.getProjects());
            return employeeRepository.save(updatedEmployee);
        }
        return null;
    }
}
