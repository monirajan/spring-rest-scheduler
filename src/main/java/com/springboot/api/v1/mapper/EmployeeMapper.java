package com.springboot.api.v1.mapper;

import com.springboot.api.v1.model.EmployeeDTO;
import com.springboot.domain.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);
    EmployeeDTO employeeToemployeeDTO(Employee employee);
    Employee employeeDTOToemployee(EmployeeDTO employeeDTO);
}
