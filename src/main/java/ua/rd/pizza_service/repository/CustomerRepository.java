package ua.rd.pizza_service.repository;

import java.util.List;

import ua.rd.pizza_service.domain.Customer;

public interface CustomerRepository {

	void save(Customer customer);
	
	Customer find(Long id);
	
	void updateCustomer(Customer customer);
	
	List<Customer> findAll();
	
	List<Customer> findActiveCustomers();
	
	List<Customer> findDeactiveCustomer();
	
	void activateCustomer(Long id);
	
	void deleteCustomer(Long id);
	
	void banCustomer(Long id);
}
