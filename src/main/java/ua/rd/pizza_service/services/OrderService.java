package ua.rd.pizza_service.services;

import ua.rd.pizza_service.domain.Customer;
import ua.rd.pizza_service.domain.Order;

public interface OrderService {

	
	Order placeNewOrder(Customer customer, Integer ... pizzasID);
}
