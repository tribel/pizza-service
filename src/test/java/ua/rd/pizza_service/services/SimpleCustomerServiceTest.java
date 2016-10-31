package ua.rd.pizza_service.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ua.rd.pizza_service.domain.Customer;
import ua.rd.pizza_service.repository.CustomerRepository;


@RunWith(MockitoJUnitRunner.class)
public class SimpleCustomerServiceTest {

	@Mock
	private CustomerRepository repository;
	private CustomerService service;
	
	@Before
	public void initialize() {
		service = new SimpleCustomerService(repository);
	}
	
	@Test
	public void testFind() {
		when(repository.find(2L)).thenReturn(new Customer());
		Customer customer = service.find(2L);
		
		verify(repository, times(1)).find(2L);
		assertNotNull(customer);
	}

	@Test
	public void testUpdateCustomer() {
		Customer customer = mock(Customer.class);
		service.updateCustomer(customer);
		
		verify(repository, times(1)).updateCustomer(customer);
	}

	@Test
	public void testFindAll() {
		List<Customer> list = spy(new ArrayList<>());
		when(list.size()).thenReturn(5);
		when(repository.findAll()).thenReturn(list);
		
		List<Customer> customers = service.findAll();
		assertEquals(5, customers.size());
		verify(repository, times(1)).findAll();
	}

	@Test
	public void testFindActiveCustomers() {
		List<Customer> list = spy(new ArrayList<>());
		when(list.size()).thenReturn(10);
		when(repository.findActiveCustomers()).thenReturn(list);
		
		List<Customer> customers = service.findActiveCustomers();
		verify(repository, times(1)).findActiveCustomers();
		assertEquals(10, customers.size());
	}

	@Test
	public void testFindDeactiveCustomer() {
		List<Customer> list = spy(new ArrayList<>());
		when(list.size()).thenReturn(10);
		when(repository.findDeactiveCustomer()).thenReturn(list);
		
		List<Customer> customers = service.findDeactiveCustomer();
		verify(repository, times(1)).findDeactiveCustomer();
		assertEquals(10, customers.size());
	}

	@Test
	public void testActivateCustomer() {
		service.activateCustomer(2L);
		verify(repository, times(1)).activateCustomer(2L);
	}

	@Test
	public void testDeleteCustomer() {
		service.deleteCustomer(3L);
		verify(repository, times(1)).deleteCustomer(3L);
	}

	@Test
	public void testBanCustomer() {
		service.banCustomer(2L);
		verify(repository, times(1)).banCustomer(2L);
	}

	@Test
	public void testAddNewCustomer() {
		Customer customer = mock(Customer.class);
		service.addNewCustomer(customer);
		verify(repository, times(1)).save(customer);
	}

}
