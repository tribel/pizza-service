package ua.rd.pizza_service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.rd.pizza_service.domain.Customer;
import ua.rd.pizza_service.repository.CustomerRepository;

@Service
public class SimpleCustomerService implements CustomerService{

	private CustomerRepository customerRepo;
	
	@Autowired
	public SimpleCustomerService(CustomerRepository customerRepo) {
		this.customerRepo = customerRepo;
	}

	@Override
	public Customer find(Long id) {
		return customerRepo.find(id);
	}

	@Override
	@Transactional
	public void updateCustomer(Customer customer) {
		customerRepo.updateCustomer(customer);
	}

	@Override
	public List<Customer> findAll() {
		return customerRepo.findAll();
	}

	@Override
	public List<Customer> findActiveCustomers() {
		return customerRepo.findActiveCustomers();
	}

	@Override
	public List<Customer> findDeactiveCustomer() {
		return customerRepo.findDeactiveCustomer();
	}

	@Override
	@Transactional
	public void activateCustomer(Long id) {
		customerRepo.activateCustomer(id);
	}

	@Override
	@Transactional
	public void deleteCustomer(Long id) {
		customerRepo.deleteCustomer(id);
	}

	@Override
	@Transactional
	public void banCustomer(Long id) {
		customerRepo.banCustomer(id);
	}

	@Override
	@Transactional
	public void addNewCustomer(Customer customer) {
		customerRepo.save(customer);
	}

}
