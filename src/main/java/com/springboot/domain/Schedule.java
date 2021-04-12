package com.springboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@EqualsAndHashCode(exclude = {"employee"})
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Type(type="date")
    private Date startDate;

    @Type(type = "date")
    private Date endDate;

    private String time;
    private Integer duration;
    private Boolean repeat;

    @Enumerated(value = EnumType.STRING)
    private Frequency frequency;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="employee_id")
    @JsonIgnoreProperties("schedules")
    @NotFound(action = NotFoundAction.IGNORE)
    private Employee employee;

}
