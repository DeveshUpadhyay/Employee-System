package com.dev.employee.service;

import com.dev.employee.dto.EmployeeDTO;
import com.dev.employee.entity.Employee;
import com.dev.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    EmployeeRepository employeeRepository;

    public boolean employeeExist(EmployeeDTO employeeDTO){
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeDTO.getEmployeeNo());
        return optionalEmployee.isPresent();
    }

    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO){

        if(employeeExist(employeeDTO)){
            return new EmployeeDTO();
        }
        Employee employee = EmployeeDTO.prepareEntity(employeeDTO);
        employee = employeeRepository.save(employee);
        return EmployeeDTO.prepareDTO(employee);
    }

    public List<EmployeeDTO> getAllEmployee(){
        return employeeRepository.findAll().stream().map(x-> EmployeeDTO.prepareDTO(x)).collect(Collectors.toList());
    }

    public EmployeeDTO getEmployee(EmployeeDTO employeeDTO){
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeDTO.getEmployeeNo());
        if(optionalEmployee.isPresent()){
            return EmployeeDTO.prepareDTO(optionalEmployee.get());
        }
        return new EmployeeDTO();
    }

    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO){
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeDTO.getEmployeeNo());
        Employee resultEmployee;
        if(optionalEmployee.isPresent()){
            Employee employee = optionalEmployee.get();
            employee = EmployeeDTO.updateEntireEmployee(employee, employeeDTO);
            resultEmployee = employeeRepository.save(employee);
        }else{
//            resultEmployee = employeeRepository.save(EmployeeDTO.prepareEntity(employeeDTO));
            resultEmployee = new Employee();
        }
        return EmployeeDTO.prepareDTO(resultEmployee);
    }

    public EmployeeDTO deleteEmployee(EmployeeDTO employeeDTO){
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeDTO.getEmployeeNo());
        if(optionalEmployee.isPresent()){
            employeeRepository.deleteById(employeeDTO.getEmployeeNo());
//            employeeRepository.delete(EmployeeDTO.prepareEntity(employeeDTO));
            return EmployeeDTO.prepareDTO(optionalEmployee.get());
        }
        return new EmployeeDTO();
    }
}
