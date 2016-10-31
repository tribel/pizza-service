package ua.rd.pizza_service.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ua.rd.pizza_service.domain.CustomerAddress;

public class JpaCustomerAddressRepositoryTest extends RepositoryTestConfig{

	@Autowired
	private CustomerAddressRepository repository;
	
	@Before
	public void setUp() {
		nativeAddressSave(new CustomerAddress(1L, "street1", "1", 1));
		nativeAddressSave(new CustomerAddress(2L, "street2", "2", 2));
		nativeAddressSave(new CustomerAddress(3L, "street3", "3", 3));
	}
	
	@After
	public void clear() {
		jdbcTemplate.execute("DELETE FROM customeraddress");
	}

	
	@Test
	public void testFind() {
		CustomerAddress address = repository.find(1L);
		assertEquals("street1", address.getSreet());
		assertEquals("1", address.getBuilding());
		
		address = repository.find(3L);
		assertEquals("3", address.getBuilding());
		assertEquals(new Integer(3), address.getFlat());
	}

	@Test
	public void testFindAll() {
		List<CustomerAddress> addresses = repository.findAll();
		assertEquals(3, addresses.size());
	}

	@Test
	public void testFindAddressByCustomerId() {
		jdbcTemplate.update("INSERT INTO Customer (id, name, adress_id, card_id, status) "
				+ "values(?, ?, ?, ?, ?)", 
				1L, "name", 1L, null, "ACTIVE");
		
		CustomerAddress address = repository.findAddressByCustomerId(1L);
		assertEquals("street1", address.getSreet());
		assertEquals("1", address.getBuilding());
		
		jdbcTemplate.execute("DELETE FROM Customer");
	}

}
