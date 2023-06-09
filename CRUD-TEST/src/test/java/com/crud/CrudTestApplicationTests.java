package com.crud;

import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertEquals;

//import java.util.ArrayList;
import java.util.List;
//import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.crud.entity.Employee;
import com.crud.repository.EmployeeRepository;

@SpringBootTest

class CrudTestApplicationTests {

	@Autowired
	EmployeeRepository emprepo;

	@Test
	void posttest() {
		Employee emp = new Employee();
		//emp.setId(11);
		emp.setName("abhishek");
		emp.setCity("agra");
		emprepo.save(emp);

		assertThat(emprepo.findByName("abhishek")).isNotNull();

	}

	@Test
	void getTest() {
		List<Employee> list = emprepo.findAll();
		assertThat(list.size()).isGreaterThan(0);

	}
	
	@Test
	void getbyIdTest()
	{
		Employee emp = emprepo.findById(8).get();
		assertThat(emp.getId()).isEqualTo(8);
	}
	
	@Test
	void putTest()
	{
		Employee employee = emprepo.findById(11).get();

        employee.setName("manoj");

        Employee employeeUpdated =  emprepo.save(employee);
        assertThat(employeeUpdated.getName()).isEqualTo("manoj");
	}
	
	@Test
	void deleteTest()
	{
        Employee employee = emprepo.findById(11).get();

        emprepo.delete(employee);

        //employeeRepository.deleteById(1L);
//
//        Employee employee1 = null;
//
//        Optional<Employee> optionalEmployee = emprepo.findByName("anuj");
//
//        if(optionalEmployee.isPresent()){
//            employee1 = optionalEmployee.get();
//        }
        assertThat(emprepo.findById(11)).isEmpty();
//
	}

}
