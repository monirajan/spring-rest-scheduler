package com.springboot.bootstrap;

import com.springboot.domain.Employee;
import com.springboot.domain.Frequency;
import com.springboot.domain.Schedule;
import com.springboot.repositories.EmployeeRepository;
import com.springboot.repositories.ScheduleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;

@Component
public class DataLoader implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;
    private final ScheduleRepository scheduleRepository;

    public DataLoader(EmployeeRepository employeeRepository, ScheduleRepository scheduleRepository) {
        this.employeeRepository = employeeRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Employee employee = new Employee();
        employee.setId(1l);
        employee.setMailId("a.monisha.1995@gmail.com");
        employee.setPatientId("1");

        Schedule schedule = new Schedule();
        schedule.setId(1l);
        schedule.setStartDate(new SimpleDateFormat("dd/MM/yyyy").parse("17/11/1995"));
        schedule.setEndDate(new SimpleDateFormat("dd/MM/yyyy").parse("17/11/1995"));
        schedule.setTime("10:00");
        schedule.setDuration(60);
        schedule.setRepeat(false);
        schedule.setFrequency(null);
        Schedule savedSchedule = scheduleRepository.save(schedule);

        employee.setSchedules(savedSchedule);
        Employee savedEmployee = employeeRepository.save(employee);
        schedule.setEmployee(savedEmployee);

        Employee employee1 = new Employee();
        employee1.setId(2l);
        employee1.setMailId("abc@gmail.com");
        employee1.setPatientId("2");

        Schedule schedule1 = new Schedule();
        schedule1.setId(2l);
        schedule1.setStartDate(new SimpleDateFormat("dd/MM/yyyy").parse("18/11/1995"));
        schedule1.setEndDate(new SimpleDateFormat("dd/MM/yyyy").parse("25/11/1996"));
        schedule1.setTime("12:00");
        schedule1.setDuration(30);
        schedule1.setRepeat(true);
        schedule1.setFrequency(Frequency.Daily);
        Schedule savedSchedule1 = scheduleRepository.save(schedule1);

        employee1.setSchedules(savedSchedule1);
        Employee savedEmployee1 = employeeRepository.save(employee1);
        schedule1.setEmployee(savedEmployee1);

        System.out.println("Bootstrap data loaded");
    }
}
