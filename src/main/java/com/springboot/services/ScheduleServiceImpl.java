package com.springboot.services;

import com.springboot.api.v1.mapper.ScheduleMapper;
import com.springboot.api.v1.model.ScheduleDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService{

    private final ScheduleMapper scheduleMapper;

    public ScheduleServiceImpl(ScheduleMapper scheduleMapper) {
        this.scheduleMapper = scheduleMapper;
    }

    @Override
    public List<ScheduleDTO> listScheduleByDate(String date) {
        return null;
    }
}
