package ua.rd.pizza_service.repository;

import java.util.List;

import ua.rd.pizza_service.domain.CustomerAddress;

public interface CustomerAddressRepository {

	CustomerAddress find(Long id);
	
	List<CustomerAddress> findAll();
	
	CustomerAddress findAddressByCustomerId(Long id);

}
