package com.javaguru.timemanager.projects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProjectService {
    private ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Long create(Project project) {
        return projectRepository.save(project).getId();
    }

    public Project findById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No product found with id : " + id));
    }

    public Project findByName(String name) {
        return projectRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("No product found with name : " + name));
    }

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public void deleteById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No project found with id : " + id + " to delete"));
        projectRepository.delete(project);
    }

    public void update(Project project) {
        projectRepository.save(project);
    }
}
