package com.springboot.api.v1.model;

import com.springboot.domain.Schedule;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class EmployeeDTO {

    private Long id;
    private String mailId;
    private String patientId;
    private Set<Schedule> schedules = new HashSet<>();

}
