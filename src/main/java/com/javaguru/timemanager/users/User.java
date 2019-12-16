package com.javaguru.timemanager.users;

import com.javaguru.timemanager.projects.Project;
import com.javaguru.timemanager.timereports.Timereport;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Month;
import java.util.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "name")
    private String name;
    @OneToMany
    @JoinColumn(name = "user_id")
    private Set<Timereport> timereports = new HashSet<>();

    public User() {
    }

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

    public Set<Timereport> getTimereports() {
        return timereports;
    }

    public void setTimereports(Set<Timereport> timereports) {
        this.timereports = timereports;
    }

    public void addTimereport(Timereport timereport) {
        timereports.add(timereport);
    }

    public Set<Project> getProjects() {
        Set<Project> projects = new HashSet<>();
        timereports.forEach(t -> projects.add(t.getProject()));
        return projects;
    }

    public Map<String, BigDecimal> getSalaryPerProject() {
        Map<String, BigDecimal> salaryPerProject = new HashMap<>();
        timereports.forEach(t -> {
            String projectName = t.getProject().getName();
            if (salaryPerProject.containsKey(projectName)) {
                salaryPerProject.replace(projectName, t.getSalary().add(salaryPerProject.get(projectName)));
            } else {
                salaryPerProject.put(projectName, t.getSalary());
            }
        });
        return salaryPerProject;
    }

    public Map<Month, BigDecimal> getSalaryPerMonth(int year) {
        Map<Month, BigDecimal> salaries = new HashMap<>();
        timereports.forEach(t -> {
            if (year == t.getDate().toLocalDate().getYear()) {
                Month month = t.getDate().toLocalDate().getMonth();
                if (salaries.containsKey(month)) {
                    salaries.replace(month, t.getSalary().add(salaries.get(month)));
                } else {
                    salaries.put(month, t.getSalary());
                }
            }
        });
        return salaries;
    }

    public BigDecimal getTotalSalary() {
        return timereports
                .stream()
                .map(Timereport::getSalary)
                .reduce(BigDecimal::add)
                .orElse(new BigDecimal("0"));
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", timereports=" + timereports +
                ", projects=" + getProjects() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) &&
                name.equals(user.name) &&
                Objects.equals(timereports, user.timereports);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, timereports);
    }
}
