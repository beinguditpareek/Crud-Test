package com.crud.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.entity.Employee;
import com.crud.repository.EmployeeRepository;

@RestController
@RequestMapping("/employee")
public class Controller 
{
	@Autowired
	EmployeeRepository emprepo;
	
	@PostMapping("/post")
	public ResponseEntity<String> addEmployee(@RequestBody Employee emp)
	{
		emprepo.save(emp);
		return ResponseEntity.ok("Employee Added");
	}
	
	@GetMapping("/get")
	public List<Employee> getEmployee()
	{
		List<Employee> list = new ArrayList<>();
		emprepo.findAll().forEach(list::add);
		return list;
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) 
	  {
		  Optional<Employee> emp = emprepo.findById(id);
		  if(emp.isPresent())
		  {
	  return new ResponseEntity<Employee>(emp.get(),HttpStatus.FOUND);
	  
	  }
		  else
	  {
		return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
	  }

	  }
	
	@PutMapping("/put/{id}")
	  public ResponseEntity<String> updateById(@PathVariable int id,@RequestBody Employee employee)
	  {
		  Optional<Employee> emp =  emprepo.findById(id);
		  if (emp.isPresent())
		  {
			  Employee empExist = emp.get();
			  empExist.setName(employee.getName());
			  empExist.setCity(employee.getCity());
			  
			  emprepo.save(empExist);
			  return ResponseEntity.ok("Details Updated");
			  
		  }
		  else
		  {
			  return ResponseEntity.ok("Details Not Found");
		  }
	  }
	  
	  @DeleteMapping("/employee/{id}")
	  public ResponseEntity<String> deleteById(@PathVariable int id)
	  {
		  emprepo.deleteById(id);
		  
		  return ResponseEntity.ok("Deleted");
	  }
	  

}
