package com.anony18.s4crud.rest;

import com.anony18.s4crud.entity.Employee;
import com.anony18.s4crud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    //quick and dirty: inject employee dao

    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService){
        employeeService = theEmployeeService;
    }

    // expose "/employees" and return a list of employees
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    // add mapping gor GET /employees/{employeesID}
    @GetMapping("/employees/{employeesID}")
    public Employee getEmployee(@PathVariable int employeesID){
        Employee theEmployee = employeeService.findById(employeesID);

        if(theEmployee == null){
            throw new RuntimeException("Employee id not found: " + employeesID);
        }
        return theEmployee;
    }

    //add mapping for POST/ employees - add new employee
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee){

        theEmployee.setId(0);
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }

    //add mapping for PUT/ employees - update employee
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee){
        return employeeService.save(theEmployee);
    }

    // add mapping for DELETE/employees/{employeeID} - delete employee

    @DeleteMapping("/employees/{employeeID}")
    public String deleteEmployee(@PathVariable int employeeID){
        Employee dbEmployee = employeeService.findById(employeeID);
        if (dbEmployee == null){
            throw new RuntimeException("Employee id not found" + employeeID);
        }
        employeeService.deleteById(employeeID);
        return "Deleted employee id: " + employeeID;
    }
}
