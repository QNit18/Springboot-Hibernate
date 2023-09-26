package com.anony18.s4crud.service;

import com.anony18.s4crud.dao.EmployeeRepository;
import com.anony18.s4crud.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    // inject Employee DAO
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository theEmployee) {
        employeeRepository = theEmployee;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int theId) {
        Optional<Employee> result = employeeRepository.findById(theId);

        Employee theEmployee = null;
        if(result.isPresent()){
            theEmployee = result.get();
        }else{
            throw new RuntimeException("Did not find employee id: " + theId);
        }
        return theEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {
        return employeeRepository.save(theEmployee);
    }

    @Override
    public void deleteById(int theId) {
        employeeRepository.deleteById(theId);
    }
}
