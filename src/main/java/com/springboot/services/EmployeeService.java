package com.springboot.services;

import com.springboot.api.v1.model.EmployeeDTO;
import com.springboot.api.v1.model.ScheduleDTO;
import com.springboot.domain.Schedule;

import java.text.ParseException;
import java.util.List;

public interface EmployeeService {

    List<EmployeeDTO> getAllEmployees();
    EmployeeDTO createEmployeeSchedule(EmployeeDTO employeeDTO);
    ScheduleDTO listScheduleByEmployeeId(String id);
    EmployeeDTO modifyScheduleByEmployeeId(String id, ScheduleDTO scheduleDTO);
    void cancelScheduleByEmployeeId(String id, ScheduleDTO scheduleDTO);
    List<ScheduleDTO> listScheduleByDate(String date);

}
