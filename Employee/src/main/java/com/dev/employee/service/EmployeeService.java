package com.dev.employee.service;

import com.dev.employee.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    public boolean employeeExist(EmployeeDTO employee);
    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO);

    public EmployeeDTO getEmployee(EmployeeDTO employeeDTO);

    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO);

    public EmployeeDTO deleteEmployee(EmployeeDTO employeeDTO);

    public List<EmployeeDTO> getAllEmployee();
}
