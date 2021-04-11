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
import java.util.Iterator;
import java.util.List;
import java.util.Set;
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
        Set<Schedule> scheduleSet = employee.getSchedules();
        Iterator iterator = scheduleSet.iterator();
        while (iterator.hasNext()) {
            Schedule schedule = (Schedule) iterator.next();
            employee.addSchedule(schedule);
        }
        employee = employeeRepository.save(employee);
        scheduleSet = employee.getSchedules();
        iterator = scheduleSet.iterator();
        while (iterator.hasNext()) {
            Schedule schedule = (Schedule) iterator.next();
            scheduleRepository.save(schedule);
        }

        return employeeMapper.employeeToemployeeDTO(employee);
    }

    @Override
    public List<ScheduleDTO> listScheduleByEmployeeId(String id) {
        Employee employee = employeeRepository.findByMailId(id);
        Set<Schedule> scheduleSet = employee.getSchedules();
        return scheduleSet.stream()
                .map(scheduleMapper::scheduleToscheduleDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO modifyScheduleByEmployeeId(String id, ScheduleDTO scheduleDTO){

        Schedule scheduleToModify = scheduleMapper.scheduleDTOToschedule(scheduleDTO);

        Schedule schedule = scheduleRepository.findById(scheduleToModify.getId()).get();

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

        Schedule savedSchedule = scheduleRepository.save(schedule);

        Employee employee = employeeRepository.findByMailId(id);
        Set<Schedule> empSchedule = employee.getSchedules();
        empSchedule.forEach(schedule1 -> {
            if(schedule1.getId() == savedSchedule.getId())
                schedule1 = savedSchedule;
        });
        employee.setSchedules(empSchedule);
        Employee savedEmp = employeeRepository.save(employee);
        return employeeMapper.employeeToemployeeDTO(savedEmp);
    }

    @Override
    public void cancelScheduleByEmployeeId(String id, ScheduleDTO scheduleDTO) {

        Schedule scheduleToCancel = scheduleMapper.scheduleDTOToschedule(scheduleDTO);

        Employee employee = employeeRepository.findByMailId(id);
        Set<Schedule> scheduleSet = employee.getSchedules();
        scheduleSet.forEach(schedule -> {
            if(schedule.getId() == scheduleToCancel.getId())
                scheduleSet.remove(schedule);
        });
        scheduleRepository.deleteById(scheduleToCancel.getId());
        employee.setSchedules(scheduleSet);
        employeeRepository.save(employee);
    }

}
