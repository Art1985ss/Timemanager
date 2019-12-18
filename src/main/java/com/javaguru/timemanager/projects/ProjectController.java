package com.javaguru.timemanager.projects;

import com.javaguru.timemanager.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {
    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public ResponseEntity<List<ProjectDto>> findAll() {
        List<ProjectDto> projectsDto = projectService.findAll().stream()
                .map(Converter::convertToDto).collect(Collectors.toList());
        return ResponseEntity.ok(projectsDto);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ProjectDto projectDto) {
        Long id = projectService.create(Converter.convertFromDto(projectDto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.status(HttpStatus.CREATED).location(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> findById(@PathVariable Long id) {
        ProjectDto projectDto = Converter.convertToDto(projectService.findById(id));
        return ResponseEntity.ok(projectDto);
    }

    @GetMapping("/")
    public ResponseEntity<ProjectDto> findByName(@RequestParam String name) {
        ProjectDto projectDto = Converter.convertToDto(projectService.findByName(name));
        return ResponseEntity.ok(projectDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        projectService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody ProjectDto projectDto) {
        projectService.update(Converter.convertFromDto(projectDto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(projectDto.getId())
                .toUri();
        return ResponseEntity.status(HttpStatus.ACCEPTED).location(location).build();
    }
}
