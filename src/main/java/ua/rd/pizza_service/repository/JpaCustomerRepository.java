package ua.rd.pizza_service.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import ua.rd.pizza_service.domain.Customer;
import ua.rd.pizza_service.domain.Customer.CustomerStatus;

@Repository("customerRepository")
public class JpaCustomerRepository implements CustomerRepository{

	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Customer find(Integer id) {
		return em.find(Customer.class, id);
	}

	@Override
	public void updateCustomer(Customer customer) {
		em.merge(customer);
	}

	@Override
	public List<Customer> findAll() {
		return em.createQuery("SELECT * FROM Customer c", Customer.class).getResultList();
	}

	@Override
	public List<Customer> findActiveCustomers() {
		List<Customer> customers = em.createQuery("SELECT * FROM Customer c WHERE c.status = :sts ", 
						Customer.class)
				.setParameter("sts", CustomerStatus.ACTIVE)
				.getResultList();
		
		return customers;
	}

	@Override
	public List<Customer> findDeactiveCustomer() {
		List<Customer> customers = em.createQuery("SELECT * FROM Customer c WHERE c.status != :sts ", 
				Customer.class)
			.setParameter("sts", CustomerStatus.ACTIVE)
			.getResultList();

		return customers;
	}

	@Override
	public void activateCustomer(Integer id) {
		find(id).setStatus(CustomerStatus.ACTIVE);
	}

	@Override
	public void deleteCustomer(Integer id) {
		find(id).setStatus(CustomerStatus.DELETED);
		
	}

	@Override
	public void banCustomer(Integer id) {
		find(id).setStatus(CustomerStatus.BANNED);
	}

}
