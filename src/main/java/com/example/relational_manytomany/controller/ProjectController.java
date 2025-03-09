package com.example.relational_manytomany.controller;

import com.example.relational_manytomany.model.Employee;
import com.example.relational_manytomany.model.Project;
import com.example.relational_manytomany.repository.ProjectRepository;
import com.example.relational_manytomany.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectRepository projectRepository;

    @PostMapping("/add")
    public void addProject(@RequestBody Project project) {
        projectService.add(project);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteProject(@PathVariable long id) {
        projectService.delete(id);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Project> editProject(@PathVariable long id, @RequestBody Project project) {
        Project existingProject = projectRepository.findById(id).orElse(null);

        if (existingProject == null) {
            return ResponseEntity.notFound().build();
        }
        for (Employee employee : project.getEmployees()) {
            employee.getProjects().add(existingProject);
        }

        existingProject.setTitle(project.getTitle());
        existingProject.setEmployees(project.getEmployees());
        Project updatedProject = projectRepository.save(existingProject);
        return ResponseEntity.ok(updatedProject);
    }
}
