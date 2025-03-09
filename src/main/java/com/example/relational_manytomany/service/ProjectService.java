package com.example.relational_manytomany.service;
import com.example.relational_manytomany.model.Project;

public interface ProjectService {

    void add(Project project);
    void delete(Long id);
    Project updateProject(Long id, Project project);

}
