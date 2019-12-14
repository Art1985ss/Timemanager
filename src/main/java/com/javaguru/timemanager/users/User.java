package com.javaguru.timemanager.users;

import com.javaguru.timemanager.projects.Project;
import com.javaguru.timemanager.timereports.Timereport;

import javax.persistence.*;
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
