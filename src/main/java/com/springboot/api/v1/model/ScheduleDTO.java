package com.springboot.api.v1.model;

import com.springboot.domain.Employee;
import com.springboot.domain.Frequency;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import java.util.Date;

@Data
public class ScheduleDTO {

    private Long id;
    private Date startDate;
    private Date endDate;
    private String time;
    private Integer duration;
    private Boolean repeat;
    private Frequency frequency;
    private Employee employee;
}
