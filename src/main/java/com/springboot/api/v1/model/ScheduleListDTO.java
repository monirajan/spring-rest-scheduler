package com.springboot.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ScheduleListDTO {
    List<ScheduleDTO> scheduleDTOList;
}
