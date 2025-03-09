package com.example.relational_manytomany.controller;
import com.example.relational_manytomany.model.Project;
import org.springframework.http.ResponseEntity;
import com.example.relational_manytomany.model.Employee;
import com.example.relational_manytomany.repository.EmployeeRepository;
import com.example.relational_manytomany.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/add")
    public void add(@RequestBody Employee employee){
        employeeService.addEmployee(employee);
    }
    @GetMapping("/sort")
    public List<Employee> findAll() {
        return employeeService.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }
    @GetMapping("/get/{id}")
    public Employee getEmployee(@PathVariable Long id){
        return employeeService.getEmployeeById(id);
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        employeeService.deleteEmployee(id);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        Employee existingEmployee = employeeRepository.findById(id).orElse(null);

        if (existingEmployee == null) {
            return ResponseEntity.notFound().build();
        }
        for (Project project : employee.getProjects()) {
            project.getEmployees().add(existingEmployee);
        }

        existingEmployee.setName(employee.getName());
        existingEmployee.setProjects(employee.getProjects());
        Employee updatedEmployee = employeeRepository.save(existingEmployee);
        return ResponseEntity.ok(updatedEmployee);
    }

}
