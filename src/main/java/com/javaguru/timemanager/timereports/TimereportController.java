package com.javaguru.timemanager.timereports;

import com.javaguru.timemanager.Converter;
import com.javaguru.timemanager.projects.Project;
import com.javaguru.timemanager.projects.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/timereports")
public class TimereportController {
    private TimereportService timereportService;
    private ProjectService projectService;

    @Autowired
    public TimereportController(TimereportService timereportService, ProjectService projectService) {
        this.timereportService = timereportService;
        this.projectService = projectService;
    }

    @GetMapping
    public ResponseEntity<List<TimereportDto>> getAll() {
        List<TimereportDto> timereportDtos = new ArrayList<>();
        timereportService.getAll().forEach(t -> timereportDtos.add(Converter.convertToDto(t)));
        return ResponseEntity.ok(timereportDtos);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody TimereportDto timereportDto) {
        Long id = timereportService.create(Converter.convertFromDto(timereportDto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.status(HttpStatus.CREATED).location(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TimereportDto> findById(@PathVariable Long id) {
        TimereportDto timereportDto = Converter.convertToDto(timereportService.findById(id));
        return ResponseEntity.status(HttpStatus.FOUND).body(timereportDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        timereportService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody TimereportDto timereportDto) {
        timereportService.update(Converter.convertFromDto(timereportDto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(timereportDto.getId())
                .toUri();
        return ResponseEntity.status(HttpStatus.ACCEPTED).location(location).build();
    }

    @PutMapping("/{id}/projects/project_id")
    public ResponseEntity<Void> setProject(@PathVariable Long id, @PathVariable Long projectId) {
        Timereport timereport = timereportService.findById(id);
        Project project = projectService.findById(projectId);
        timereport = timereportService.setProject(timereport, project);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(timereport.getId())
                .toUri();
        return ResponseEntity.status(HttpStatus.ACCEPTED).location(location).build();
    }

}
