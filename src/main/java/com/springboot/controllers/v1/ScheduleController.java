package com.springboot.controllers.v1;

import com.springboot.api.v1.model.ScheduleDTO;
import com.springboot.api.v1.model.ScheduleListDTO;
import com.springboot.services.ScheduleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/api/v1/schedules/{date}/")
    public ScheduleListDTO getScheduleByDate(@PathVariable String date)
    {
        return new ScheduleListDTO(scheduleService.listScheduleByDate(date));
    }
}
