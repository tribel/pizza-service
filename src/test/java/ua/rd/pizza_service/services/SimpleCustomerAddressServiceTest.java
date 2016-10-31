package ua.rd.pizza_service.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ua.rd.pizza_service.domain.CustomerAddress;
import ua.rd.pizza_service.repository.CustomerAddressRepository;

@RunWith(MockitoJUnitRunner.class)
public class SimpleCustomerAddressServiceTest {

	@Mock
	private CustomerAddressRepository repository;
	private CustomerAddressService service;
	
	@Before
	public void initialize() {
		service = new SimpleCustomerAddressService(repository);
	}
	
	@Test
	public void testFind() {
		when(repository.find(1L)).thenReturn(new CustomerAddress(1L));
		CustomerAddress address = service.find(1L);
		
		verify(repository, times(1)).find(1L);
		assertEquals(new Long(1), address.getId());
	}

	@Test
	public void testFindAll() {
		List<CustomerAddress> list = spy(new ArrayList<>());
		when(list.size()).thenReturn(7);
		when(repository.findAll()).thenReturn(list);
	
		List<CustomerAddress> addresses = service.findAll();
		verify(repository, times(1)).findAll();
		assertEquals(7, addresses.size());
	}

	@Test
	public void testFindAddressByCustomerId() {
		when(repository.findAddressByCustomerId(1L)).thenReturn(new CustomerAddress(2L));
		
		CustomerAddress address = service.findAddressByCustomerId(1L);
		verify(repository, times(1)).findAddressByCustomerId(1L);
		assertEquals(new Long(2), address.getId());
	}

}
