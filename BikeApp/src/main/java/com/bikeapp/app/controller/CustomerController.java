package com.bikeapp.app.controller;

import java.util.ArrayList;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bikeapp.app.model.Customer;
import com.bikeapp.app.repository.CustomerRepository;
 
 
@RestController
@RequestMapping(value="/api")
public class CustomerController {
 
	@Autowired
	CustomerRepository repository;
 
	@GetMapping(value="/customer",  produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Customer> getAll() {
		List<Customer> list = new ArrayList<>();
		Iterable<Customer> customers = repository.findAll();
 
		customers.forEach(list::add);
		return list;
	}
	
	@PostMapping(value="/postcustomer" ,consumes=MediaType.APPLICATION_JSON_VALUE)
	public Customer postCustomer(@RequestBody Customer customer) {
 
		repository.save(new Customer(customer.getFirstName(), customer.getLastName()));
		return customer;
	}
 
	@GetMapping(value="/findbylastname/{lastName}",  produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Customer> findByLastName(@PathVariable String lastName) {
 
		List<Customer> customers = repository.findByLastName(lastName);
		return customers;
	}
	
	@DeleteMapping(value="/customer/{id}")
	public void deleteCustomer(@PathVariable long id){
		
		repository.deleteById(id);
	}
}