package com.javaguru.timemanager.users;

import com.javaguru.timemanager.timereports.TimereportDto;

import java.math.BigDecimal;
import java.time.Month;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UserDto {

    private Long id;
    private String name;
    private Set<TimereportDto> timereports = new HashSet<>();
    private Map<String, BigDecimal> salaryPerProject;
    private BigDecimal totalSalary;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<TimereportDto> getTimereports() {
        return timereports;
    }

    public void setTimereports(Set<TimereportDto> timereports) {
        this.timereports = timereports;
    }

    public Map<String, BigDecimal> getSalaryPerProject() {
        return salaryPerProject;
    }

    public void setSalaryPerProject(Map<String, BigDecimal> salaryPerProject) {
        this.salaryPerProject = salaryPerProject;
    }

    public BigDecimal getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(BigDecimal totalSalary) {
        this.totalSalary = totalSalary;
    }
}
