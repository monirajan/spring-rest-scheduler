package com.springboot.api.v1.model;

import com.springboot.domain.Frequency;
import lombok.Data;


@Data
public class ScheduleDTO {

    private Long id;
    private String startDate;
    private String endDate;
    private String time;
    private Integer duration;
    private Boolean repeat;
    private Frequency frequency;

}
