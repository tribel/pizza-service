package ua.rd.pizza_service.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ua.rd.pizza_service.domain.AccumulativeCard;
import ua.rd.pizza_service.domain.Customer;
import ua.rd.pizza_service.domain.CustomerAddress;

public class JpaCustomerRepositoryIT extends RepositoryTestConfig{

	@Autowired
	private CustomerRepository repository;
	
	@Before
	public void setUp() {
		CustomerAddress address = new CustomerAddress(1L, "str1", "1", 1);
		AccumulativeCard card = new AccumulativeCard(1L, 0);
		saveCustomer(new Customer(1L, "cust1", address, card), address, card);
		
		address = new CustomerAddress(2L, "str2", "2", 2);
		card = new AccumulativeCard(2L, 0);
		saveCustomer(new Customer(2L, "cust3", address, card), address, card);
		
		address = new CustomerAddress(3L, "str3", "3", 3);
		card = new AccumulativeCard(3L, 0);
		saveCustomer(new Customer(3L, "cust3", address, card), address, card);
		
	}
	
	@After
	public void clear() {
		jdbcTemplate.execute("DELETE FROM Customer");
		jdbcTemplate.execute("DELETE FROM customeraddress");
		jdbcTemplate.execute("DELETE FROM accumulativecard");
	}


	@Test 
	public void testFindAll() {
		List<Customer> customers = repository.findAll();
		assertEquals(3, customers.size());
	}

	@Test 	
	public void testFindActiveCustomers() {
		jdbcTemplate.update("UPDATE Customer SET status = 'DELETED' WHERE id = 1 ");
		
		List<Customer> customers = repository.findActiveCustomers();
		assertEquals(2, customers.size());
	}

	@Test
	public void testFindDeactiveCustomer() {
		jdbcTemplate.update("UPDATE Customer SET status = 'DELETED' WHERE id = 1 ");
		jdbcTemplate.update("UPDATE Customer SET status = 'BANNED' WHERE id = 2 ");
		
		List<Customer> customers = repository.findDeactiveCustomer();
		assertEquals(2, customers.size());
		
	}

	@Test
	public void testActivateCustomer() {
		repository.activateCustomer(2L);
	
		String customerSts = jdbcTemplate.queryForObject("SELECT c.status FROM Customer c WHERE c.id = 2", String.class);
		assertEquals("ACTIVE", customerSts);
	}

	@Test
	public void testDeleteCustomer() {
		repository.deleteCustomer(1L);
		
		assertEquals(1, repository.findDeactiveCustomer().size());
	}

	@Test 	
	public void testBanCustomer() {
		repository.banCustomer(3L);
		repository.banCustomer(1L);
		
		assertEquals(2, repository.findDeactiveCustomer().size());
	}


	
}
