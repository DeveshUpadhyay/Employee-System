package com.dev.employee.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {

    @Id
    int employeeNo;
    String employeeName;
    String designation;
    boolean isProjectAllocated;
    String projectName;



}
