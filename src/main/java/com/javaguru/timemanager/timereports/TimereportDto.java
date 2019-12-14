package com.javaguru.timemanager.timereports;

import com.javaguru.timemanager.projects.ProjectDto;

import java.math.BigDecimal;

public class TimereportDto {
    private Long id;
    private BigDecimal hours;
    private ProjectDto projectDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getHours() {
        return hours;
    }

    public void setHours(BigDecimal hours) {
        this.hours = hours;
    }

    public ProjectDto getProjectDto() {
        return projectDto;
    }

    public void setProjectDto(ProjectDto projectDto) {
        this.projectDto = projectDto;
    }
}
