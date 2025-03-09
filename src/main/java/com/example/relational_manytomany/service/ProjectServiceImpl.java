package com.example.relational_manytomany.service;

import com.example.relational_manytomany.model.Employee;
import com.example.relational_manytomany.model.Project;
import com.example.relational_manytomany.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public void add(Project project) {
        for (Employee employee : project.getEmployees()) {
            employee.getProjects().add(project); 
        }
        projectRepository.save(project);
    }
    @Override
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }

    @Override
    public Project updateProject(Long id, Project project) {
        Project updatedProject = projectRepository.findById(id).orElse(null);
        if (updatedProject != null) {
            updatedProject.setTitle(project.getTitle());
            updatedProject.setEmployees(project.getEmployees());
            return projectRepository.save(updatedProject);
        } else {
            return null;
        }
    }
}
