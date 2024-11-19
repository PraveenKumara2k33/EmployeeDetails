package code.logic.tamil.ems_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import code.logic.tamil.ems_backend.dto.EmployeeDto;
import code.logic.tamil.ems_backend.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;


@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	
	private EmployeeService employeeService;
	
	
	//Build Add Employee Rest API
	@PostMapping
	public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
		EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
		return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
	}
	//Build Get employee Rest API
	@GetMapping("{id}")
	public ResponseEntity<EmployeeDto> getEmployeeId(@PathVariable("id") Long employeeId){
		EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
		return new ResponseEntity<>(employeeDto, HttpStatus.OK);
	}
	
	//Build Get All Employees
	
	@GetMapping
	public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
		List<EmployeeDto> employees = employeeService.getAllEmployees();
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}
	
	
	public String putMethodName(@PathVariable String id, @RequestBody String entity) {
		//TODO: process PUT request
		
		return entity;
	}
	//Build Updated Employee Rest API
	@PutMapping("{id}")
	public  ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId, @RequestBody EmployeeDto updatedEmployee){
		EmployeeDto employeeDto = employeeService.updateEmployee(employeeId, updatedEmployee);
		return new ResponseEntity<>(employeeDto, HttpStatus.OK);
	}
	
	//Build Delete Employee Rest API
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId){
		employeeService.deleteEmployee(employeeId);
		return ResponseEntity.ok("Employee Deleted Successfully!");
	}
}
