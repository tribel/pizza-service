package ua.rd.pizza_service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.rd.pizza_service.domain.CustomerAddress;
import ua.rd.pizza_service.repository.CustomerAddressRepository;

@Service
public class SimpleCustomerAddressService implements CustomerAddressService{

	private CustomerAddressRepository caRepository;

	@Autowired
	public SimpleCustomerAddressService(CustomerAddressRepository caRepository) {
		this.caRepository = caRepository;
	}

	@Override
	public CustomerAddress find(Long id) {
		return caRepository.find(id);
	}

	@Override
	public List<CustomerAddress> findAll() {
		return caRepository.findAll();
	}

	@Override
	public CustomerAddress findAddressByCustomerId(Long id) {
		return caRepository.findAddressByCustomerId(id);
	}

}
