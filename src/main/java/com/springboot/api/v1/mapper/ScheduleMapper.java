package com.springboot.api.v1.mapper;

import com.springboot.api.v1.model.ScheduleDTO;
import com.springboot.domain.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ScheduleMapper {

    ScheduleMapper INSTANCE = Mappers.getMapper(ScheduleMapper.class);
    ScheduleDTO scheduleToscheduleDTO(Schedule schedule);
    Schedule scheduleDTOToschedule(ScheduleDTO scheduleDTO);
}
