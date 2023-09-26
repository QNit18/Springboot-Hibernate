package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	// since only one constructor, @Autowired is optional
	private EmployeeService employeeService;
	public EmployeeController(EmployeeService theEmployeeService){
		employeeService = theEmployeeService; // Constructor injection
	}

	// add mapping for "/list"
	@GetMapping("/list")
	public String listEmployees(Model theModel) {

		// get the employees from db
		List<Employee> theEmployees = employeeService.findAll();
		// add to the spring model
		theModel.addAttribute("employees", theEmployees);

		return "employees/list-employees";
	}

	// add mapping for /save
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel){

		// create model attribute to bind form data
		Employee theEmployee = new Employee();
		theModel.addAttribute("employee", theEmployee);

		return "employees/employee-form";
	}

	@PostMapping("/save")
	public String savevEmployee(@ModelAttribute("employees") Employee theEmployee){
		// save the employee
		employeeService.save(theEmployee);

		// use a redicrect to prevent duplicate submissions
		return "redirect:/employees/list";
	}

	// add mapping for /update
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel){

		// get the employee from the service
		Employee theEmployee = employeeService.findById(theId);

		// set employee as a model attribute to pre-populate the form
		theModel.addAttribute("employee", theEmployee);

		// send over to our form
		return "employees/employee-form";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int theId){
		// delete the employee
		employeeService.deleteById(theId);

		// redirect to /employees/list
		return "redirect:/employees/list";
	}


//	// load employee data
//
//	private List<Employee> theEmployees;
//
//	@PostConstruct
//	private void loadData() { // in-memory version
//
//		// create employees
//		Employee emp1 = new Employee("Leslie", "Andrews", "leslie@luv2code.com");
//		Employee emp2 = new Employee("Emma", "Baumgarten", "emma@luv2code.com");
//		Employee emp3 = new Employee("Avani", "Gupta", "avani@luv2code.com");
//
//		// create the list
//		theEmployees = new ArrayList<>();
//
//		// add to the list
//		theEmployees.add(emp1);
//		theEmployees.add(emp2);
//		theEmployees.add(emp3);
//	}
//
}









