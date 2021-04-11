package com.springboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date startDate;
    private Date endDate;
    private String time;
    private Integer duration;
    private Boolean repeat;

    @Enumerated(value = EnumType.STRING)
    private Frequency frequency;

    @ManyToOne
    @JoinColumn(name="employee_id")
    @JsonIgnoreProperties("schedules")
    private Employee employee;

}
