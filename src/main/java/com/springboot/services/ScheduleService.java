package com.springboot.services;

import com.springboot.api.v1.model.ScheduleDTO;

import java.text.ParseException;
import java.util.List;

public interface ScheduleService {

    List<ScheduleDTO> listAllSchedules();
    List<ScheduleDTO> listScheduleByDate(String date) throws ParseException;

}
