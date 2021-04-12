package com.springboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"schedules"})
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mailId;
    private String patientId;

    @OneToMany(mappedBy = "employee", cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonIgnoreProperties("employee")
    private Set<Schedule> schedules = new HashSet<>();

    public Employee addSchedule(Schedule schedule)
    {
        if(schedule!=null) {
            schedule.setEmployee(this);
            this.schedules.add(schedule);
            return this;
        }
        return null;
    }
}
