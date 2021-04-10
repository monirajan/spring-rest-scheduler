package com.springboot.api.v1.model;

import com.springboot.domain.Schedule;
import lombok.Data;

@Data
public class EmployeeDTO {

    private Long id;
    private String mailId;
    private String patientId;
    private Schedule schedules;

}
