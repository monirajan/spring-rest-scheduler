package com.springboot.bootstrap;

import com.springboot.domain.Employee;
import com.springboot.domain.Frequency;
import com.springboot.domain.Schedule;
import com.springboot.repositories.EmployeeRepository;
import com.springboot.repositories.ScheduleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

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

        Schedule schedule2 = new Schedule();
        schedule2.setId(2l);
        schedule2.setStartDate(new SimpleDateFormat("dd/MM/yyyy").parse("11/04/2021"));
        schedule2.setEndDate(new SimpleDateFormat("dd/MM/yyyy").parse("30/06/2021"));
        schedule2.setTime("10:00");
        schedule2.setDuration(60);
        schedule2.setRepeat(true);
        schedule2.setFrequency(Frequency.Monthly);

        employee.addSchedule(schedule);
        employee.addSchedule(schedule2);
        employeeRepository.save(employee);
        scheduleRepository.save(schedule);
        scheduleRepository.save(schedule2);

        Employee employee1 = new Employee();
        employee1.setId(2l);
        employee1.setMailId("abc@gmail.com");
        employee1.setPatientId("2");

        Schedule schedule1 = new Schedule();
        schedule1.setId(3l);
        schedule1.setStartDate(new SimpleDateFormat("dd/MM/yyyy").parse("18/11/1995"));
        schedule1.setEndDate(new SimpleDateFormat("dd/MM/yyyy").parse("25/11/1996"));
        schedule1.setTime("12:00");
        schedule1.setDuration(30);
        schedule1.setRepeat(true);
        schedule1.setFrequency(Frequency.Daily);

        Schedule schedule3 = new Schedule();
        schedule3.setId(4l);
        schedule3.setStartDate(new SimpleDateFormat("dd/MM/yyyy").parse("11/01/2021"));
        schedule3.setEndDate(new SimpleDateFormat("dd/MM/yyyy").parse("30/03/2021"));
        schedule3.setTime("15:00");
        schedule3.setDuration(15);
        schedule3.setRepeat(true);
        schedule3.setFrequency(Frequency.Weekdays);

        employee1.addSchedule(schedule1);
        employee1.addSchedule(schedule3);
        employeeRepository.save(employee1);
        scheduleRepository.save(schedule1);
        scheduleRepository.save(schedule3);

        System.out.println("Bootstrap data loaded");
    }
}
