package com.example.relational_manytomany.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIgnoreProperties("employees")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    //Bidirectional
    @ManyToMany(mappedBy = "projects", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Employee> employees = new HashSet<>();

    public Project() {}
    public Project(String title,Set<Employee> employees) {
        this.title = title;
        this.employees = employees;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}
