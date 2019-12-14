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
        userDto.setTimereports(timereportDtos);
        return userDto;
    }

    public static ProjectDto convertToDto(Project project) {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(project.getId());
        projectDto.setName(project.getName());
        projectDto.setRate(project.getRate());
        return projectDto;
    }

    public static TimereportDto convertToDto(Timereport timereport) {
        TimereportDto timereportDto = new TimereportDto();
        timereportDto.setId(timereport.getId());
        timereportDto.setHours(timereport.getHours());
        timereportDto.setProjectDto(convertToDto(timereport.getProject()));
        return timereportDto;
    }
}
