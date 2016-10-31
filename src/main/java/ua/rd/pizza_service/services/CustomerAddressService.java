package ua.rd.pizza_service.services;

import java.util.List;

import ua.rd.pizza_service.domain.CustomerAddress;

public interface CustomerAddressService {

	CustomerAddress find(Long id);
	
	List<CustomerAddress> findAll();
	
	CustomerAddress findAddressByCustomerId(Long id);
}
