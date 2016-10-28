package ua.rd.pizza_service.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
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
	/*
		saveCustomer(new Customer(1L, "cust1", new CustomerAddress(1L, "str1", "1", 1), new AccumulativeCard()));
		saveCustomer(new Customer(2L, "cust2", new CustomerAddress(2L, "str2", "2", 2), new AccumulativeCard()));
		saveCustomer(new Customer(3L, "cust3", new CustomerAddress(3L, "str3", "3", 3), new AccumulativeCard()));*/
	}
	
	@After
	public void clear() {
		
	}
	
	@Test
	@Ignore
	public void testUpdateCustomer() {
	}

	@Test 
	public void testFindAll() {
		List<Customer> customers = repository.findAll();
		assertEquals(3, customers.size());
	}

	@Test 	@Ignore
	public void testFindActiveCustomers() {
	}

	@Test 	@Ignore
	public void testFindDeactiveCustomer() {
	}

	@Test 	@Ignore
	public void testActivateCustomer() {
	}

	@Test 	@Ignore
	public void testDeleteCustomer() {
	}

	@Test 	@Ignore
	public void testBanCustomer() {
	}

	private void saveCustomer(Customer c, CustomerAddress ca, AccumulativeCard card) {
	
		jdbcTemplate.update("INSERT INTO CustomerAddress (id, sreet, building, flat) "
				+ "values(?, ?, ?, ?)" ,
				ca.getId(), ca.getSreet(), ca.getBuilding(), ca.getFlat());
		
		jdbcTemplate.update("INSERT INTO AccumulativeCard (id, accumulativeSum) "
				+ "values(?, ?)" ,
				card.getId(), card.getAccumulativeSum());
		
		jdbcTemplate.update("INSERT INTO Customer (id, name, address, cart, status) "
				+ "values(?, ?, ?, ?, ?)", 
				c.getId(), c.getName(), c.getAddress(), c.getCard(), c.getStatus());	
	}
	
}
