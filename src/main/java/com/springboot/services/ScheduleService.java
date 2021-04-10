package com.springboot.services;

import com.springboot.api.v1.model.ScheduleDTO;

import java.util.List;

public interface ScheduleService {

    List<ScheduleDTO> listScheduleByDate(String date);

}
