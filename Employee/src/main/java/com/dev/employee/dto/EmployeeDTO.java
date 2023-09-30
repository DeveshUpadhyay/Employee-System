package com.dev.employee.dto;

import com.dev.employee.entity.Employee;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class EmployeeDTO {
    @NotNull(message = "EmployeeNumber Can't be null")
    int employeeNo;

    @Size(min = 3, max = 20, message = "Employee Name's Length should be between 3 and 20 ")
    String employeeName;
    String designation;

    @Email(regexp = "^(\\w{2,8}@\\b(gmail|yahoo|tinder|something).com)$", message = "Email is not valid")
    String email;
    boolean isProjectAllocated;
    String projectName;

    public static Employee prepareEntity(EmployeeDTO employeeDTO){
        Employee employee = new Employee();

        employee.setEmployeeNo(employeeDTO.getEmployeeNo());
        employee.setEmployeeName(employeeDTO.getEmployeeName());
        employee.setDesignation(employeeDTO.getDesignation());
        employee.setProjectAllocated(employeeDTO.isProjectAllocated());
        employee.setProjectName(employeeDTO.getProjectName());

        return employee;
    }

    public static EmployeeDTO prepareDTO(Employee employee){
        EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setEmployeeNo(employee.getEmployeeNo());
        employeeDTO.setEmployeeName(employee.getEmployeeName());
        employeeDTO.setDesignation(employee.getDesignation());
        employeeDTO.setProjectAllocated(employee.isProjectAllocated());
        employeeDTO.setProjectName(employee.getProjectName());

        return employeeDTO;
    }

    public static Employee updateEntireEmployee(Employee employee, EmployeeDTO employeeDTO){


        if(employeeDTO.getEmployeeName() != null){
            employee.setEmployeeName(employeeDTO.getEmployeeName());
        }

        if(employeeDTO.getDesignation() != null){
            employee.setDesignation(employeeDTO.getDesignation());
        }

        if(employeeDTO.isProjectAllocated){
            employee.setProjectAllocated(employeeDTO.isProjectAllocated());
        }

        if(employeeDTO.getProjectName()!=null){
            employee.setProjectName(employeeDTO.getProjectName());
        }
        return employee;
    }
    
}
