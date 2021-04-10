package com.springboot.services;

import com.springboot.api.v1.mapper.EmployeeMapper;
import com.springboot.api.v1.mapper.ScheduleMapper;
import com.springboot.api.v1.model.EmployeeDTO;
import com.springboot.api.v1.model.ScheduleDTO;
import com.springboot.domain.Employee;
import com.springboot.domain.Schedule;
import com.springboot.repositories.EmployeeRepository;
import com.springboot.repositories.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;
    private final ScheduleRepository scheduleRepository;
    private final EmployeeMapper employeeMapper;
    private final ScheduleMapper scheduleMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ScheduleRepository scheduleRepository, EmployeeMapper employeeMapper, ScheduleMapper scheduleMapper) {
        this.employeeRepository = employeeRepository;
        this.scheduleRepository = scheduleRepository;
        this.employeeMapper = employeeMapper;
        this.scheduleMapper = scheduleMapper;
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeMapper::employeeToemployeeDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO createEmployeeSchedule(EmployeeDTO employeeDTO) {
        Employee employee = employeeMapper.employeeDTOToemployee(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);
        scheduleRepository.save(savedEmployee.getSchedules());
        return employeeMapper.employeeToemployeeDTO(savedEmployee);
    }

    @Override
    public ScheduleDTO listScheduleByEmployeeId(String id) {
        Employee employee = employeeRepository.findByMailId(id);
        Schedule schedule = employee.getSchedules();
        return scheduleMapper.scheduleToscheduleDTO(schedule);
    }

    @Override
    public EmployeeDTO modifyScheduleByEmployeeId(String id, ScheduleDTO scheduleDTO){
        Employee employee = employeeRepository.findByMailId(id);
        Schedule empSchedule = employee.getSchedules();
        Schedule schedule = scheduleRepository.findById(empSchedule.getId()).get();

        if(scheduleDTO.getFrequency()!=null)
            schedule.setFrequency(scheduleDTO.getFrequency());
        try {
            if (scheduleDTO.getStartDate() != null)
                schedule.setStartDate(new SimpleDateFormat().parse(scheduleDTO.getStartDate()));
        }
        catch (ParseException exception){}

        try {
            if (scheduleDTO.getEndDate() != null)
                schedule.setStartDate(new SimpleDateFormat().parse(scheduleDTO.getEndDate()));
        }
        catch (ParseException exception){}

        if(scheduleDTO.getTime()!=null)
            schedule.setTime(scheduleDTO.getTime());
        if(scheduleDTO.getDuration()!=null)
            schedule.setDuration(scheduleDTO.getDuration());
        if(scheduleDTO.getRepeat()!=null)
            schedule.setRepeat(scheduleDTO.getRepeat());

        scheduleRepository.save(schedule);
        employee.setSchedules(schedule);
        Employee savedEmp = employeeRepository.save(employee);
        return employeeMapper.employeeToemployeeDTO(savedEmp);
    }

    @Override
    public void cancelScheduleByEmployeeId(String id, ScheduleDTO scheduleDTO) {

        Employee employee = employeeRepository.findByMailId(id);
        Schedule schedule = employee.getSchedules();
        scheduleRepository.deleteById(schedule.getId());

        employee.setSchedules(null);
        employeeRepository.save(employee);
    }

}
