package ua.rd.pizza_service.repository;

import java.util.List;

import ua.rd.pizza_service.domain.Customer;

public class JpaCustomerRepository implements CustomerRepository{

	@Override
	public Customer find(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Customer> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> findActiveCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> findDeactiveCustomer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void activateCustomer(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deactivateCustomer(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
