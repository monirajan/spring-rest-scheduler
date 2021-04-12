package com.springboot.api.v1.mapper;

import com.springboot.api.v1.model.ScheduleDTO;
import com.springboot.domain.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ScheduleMapper {

    ScheduleMapper INSTANCE = Mappers.getMapper(ScheduleMapper.class);

    @Mapping(source = "startDate", dateFormat = "dd MMM yyyy", target = "startDate")
    @Mapping(source = "endDate", dateFormat = "dd MMM yyyy", target = "endDate")
    ScheduleDTO scheduleToscheduleDTO(Schedule schedule);

    @Mapping(source = "startDate", dateFormat = "dd MMM yyyy", target = "startDate")
    @Mapping(source = "endDate", dateFormat = "dd MMM yyyy", target = "endDate")
    Schedule scheduleDTOToschedule(ScheduleDTO scheduleDTO);
}
