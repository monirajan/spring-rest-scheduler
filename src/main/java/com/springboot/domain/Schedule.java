package com.springboot.domain;

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

    @OneToOne
    private Employee employee;

}
