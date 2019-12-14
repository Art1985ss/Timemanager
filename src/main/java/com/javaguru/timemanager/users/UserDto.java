package com.javaguru.timemanager.users;


import com.javaguru.timemanager.timereports.TimereportDto;

import java.util.HashSet;
import java.util.Set;

public class UserDto {

    private Long id;
    private String name;
    private Set<TimereportDto> timereports = new HashSet<>();

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
}
