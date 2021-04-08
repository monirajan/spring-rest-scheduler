package com.springboot.domain;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
@Entity
public class Employee {

    @Id
    private String mailId;
    private String patientId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "schedule")
    private Set<Schedule> schedules;
}
