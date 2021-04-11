package com.springboot.controllers.v1;
import com.springboot.api.v1.model.ScheduleListDTO;
import com.springboot.services.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/api/v1/schedules")
    @ResponseStatus(HttpStatus.OK)
    public ScheduleListDTO getAllSchedules()
    {
        return new ScheduleListDTO(scheduleService.listAllSchedules());
    }

    @GetMapping("/api/v1/schedules/{date}/")
    @ResponseStatus(HttpStatus.OK)
    public ScheduleListDTO getScheduleByDate(@PathVariable String date) throws ParseException {
        return new ScheduleListDTO(scheduleService.listScheduleByDate(date));
    }
}
