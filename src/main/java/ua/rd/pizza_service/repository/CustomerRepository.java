package ua.rd.pizza_service.repository;

import java.util.List;

import ua.rd.pizza_service.domain.Customer;

public interface CustomerRepository {

	
	Customer find(Integer id);
	
	void updateCustomer(Customer customer);
	
	List<Customer> findAll();
	
	List<Customer> findActiveCustomers();
	
	List<Customer> findDeactiveCustomer();
	
	void activateCustomer(Integer id);
	
	void deleteCustomer(Integer id);
	
	void banCustomer(Integer id);
}
