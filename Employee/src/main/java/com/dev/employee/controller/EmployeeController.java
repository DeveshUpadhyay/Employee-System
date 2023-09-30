package com.dev.employee.controller;

import com.dev.employee.dto.EmployeeDTO;
import com.dev.employee.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/getAllEmployees")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){
        List<EmployeeDTO> employeeDTOs= employeeService.getAllEmployee();
        return new ResponseEntity<>(employeeDTOs, HttpStatus.OK );
    }

    @GetMapping("/getEmployee")
    public ResponseEntity<EmployeeDTO> getEmployee(@RequestBody EmployeeDTO employeeDTO){
        return new ResponseEntity<>(employeeService.getEmployee(employeeDTO), HttpStatus.OK);
    }

    @PostMapping("/addEmployee")
    public ResponseEntity<EmployeeDTO> addEmployee(@RequestBody EmployeeDTO employeeDTO){
        EmployeeDTO resultDTO = employeeService.addEmployee(employeeDTO);
        return new ResponseEntity<>(resultDTO, HttpStatus.OK);
    }

    @PutMapping("/updateEmployee")
    public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody @Valid EmployeeDTO employeeDTO){
        return new ResponseEntity<>(employeeService.updateEmployee(employeeDTO), HttpStatus.OK);
    }

    @DeleteMapping("deleteEmployee")
    public ResponseEntity<EmployeeDTO> deleteEmployee(@RequestBody EmployeeDTO employeeDTO){
        return new ResponseEntity<>(employeeService.deleteEmployee(employeeDTO), HttpStatus.OK);
    }
}
