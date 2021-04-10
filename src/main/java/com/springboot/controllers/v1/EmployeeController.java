package com.springboot.controllers.v1;

import com.springboot.api.v1.model.EmployeeDTO;
import com.springboot.api.v1.model.EmployeeListDTO;
import com.springboot.api.v1.model.ScheduleDTO;
import com.springboot.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/api/v1/employees")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeListDTO listAllEmployees()
    {
        return new EmployeeListDTO(employeeService.getAllEmployees());
    }

    @GetMapping("/api/v1/employees/{mailId}/")
    @ResponseStatus(HttpStatus.OK)
    public ScheduleDTO listScheduleByEmpMailId(@PathVariable String mailId)
    {
        ScheduleDTO scheduleDTO = employeeService.listScheduleByEmployeeId(mailId);
        return scheduleDTO;
    }

    @PostMapping("/api/v1/employees")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeDTO createEmpSchedule(@RequestBody EmployeeDTO employeeDTO)
    {
        return employeeService.createEmployeeSchedule(employeeDTO);
    }

    @PostMapping("/api/v1/employees/{mailId}/")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeDTO modifyEmpSchedule(@PathVariable String mailId,@RequestBody ScheduleDTO scheduleDTO)
    {
        return employeeService.modifyScheduleByEmployeeId(mailId,scheduleDTO);
    }

    @DeleteMapping("/api/v1/employees/{mailId}/")
    @ResponseStatus(HttpStatus.OK)
    public void cancelEmpSchedule(@PathVariable String mailId)
    {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        employeeService.cancelScheduleByEmployeeId(mailId, scheduleDTO);
    }

}
