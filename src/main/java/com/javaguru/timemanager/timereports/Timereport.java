package com.javaguru.timemanager.timereports;

import com.javaguru.timemanager.projects.Project;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "timereports")
public class Timereport {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "hours")
    private BigDecimal hours;
    @Column(name = "created")
    private Date date;
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    public Timereport() {
    }

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public BigDecimal getSalary() {
        return hours.multiply(project.getRate());
    }

    @Override
    public String toString() {
        return "Timereport{" +
                "id=" + id +
                ", hours=" + hours +
                ", project=" + project +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Timereport that = (Timereport) o;
        return id.equals(that.id) &&
                hours.equals(that.hours) &&
                Objects.equals(project, that.project);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hours, project);
    }
}
