package com.anony18.s4crud.dao;

import com.anony18.s4crud.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    // That's it ... no need to write any code!!!
}
