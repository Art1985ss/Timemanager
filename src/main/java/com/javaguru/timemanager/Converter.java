package com.javaguru.timemanager;

import com.javaguru.timemanager.projects.Project;
import com.javaguru.timemanager.projects.ProjectDto;
import com.javaguru.timemanager.timereports.Timereport;
import com.javaguru.timemanager.timereports.TimereportDto;
import com.javaguru.timemanager.users.User;
import com.javaguru.timemanager.users.UserDto;

import java.util.HashSet;
import java.util.Set;

public class Converter {

    public static UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        Set<TimereportDto> timereportDtos = new HashSet<>();
        user.getTimereports().forEach(t -> timereportDtos.add(convertToDto(t)));
        userDto.setTimereportsDto(timereportDtos);
        userDto.setSalaryPerProject(user.getSalaryPerProject());
        userDto.setTotalSalary(user.getTotalSalary());
        return userDto;
    }

    public static User convertFromDto(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        Set<Timereport> timereports = new HashSet<>();
        userDto.getTimereportsDto().forEach(t -> timereports.add(Converter.convertFromDto(t)));
        user.setTimereports(timereports);
        return user;
    }

    public static ProjectDto convertToDto(Project project) {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(project.getId());
        projectDto.setName(project.getName());
        projectDto.setRate(project.getRate());
        return projectDto;
    }

    public static Project convertFromDto(ProjectDto projectDto) {
        Project project = new Project();
        project.setId(projectDto.getId());
        project.setName(projectDto.getName());
        project.setRate(projectDto.getRate());
        return project;
    }

    public static TimereportDto convertToDto(Timereport timereport) {
        TimereportDto timereportDto = new TimereportDto();
        timereportDto.setId(timereport.getId());
        timereportDto.setHours(timereport.getHours());
        timereportDto.setProjectDto(convertToDto(timereport.getProject()));
        return timereportDto;
    }

    public static Timereport convertFromDto(TimereportDto timereportDto) {
        Timereport timereport = new Timereport();
        timereport.setId(timereportDto.getId());
        timereport.setHours(timereportDto.getHours());
        timereport.setProject(Converter.convertFromDto(timereportDto.getProjectDto()));
        return timereport;
    }
}
