package ua.rd.pizza_service.repository;

import java.util.ArrayList;
import java.util.List;

import ua.rd.pizza_service.domain.Order;

public class InMemoryOrderRepository implements OrderRepository{

	List<Order> orders = new ArrayList<>();
	
	public InMemoryOrderRepository() {
	}

	@Override
	public Order saveOrder(Order order) {
		orders.add(order);
		return order;
	}


	
	
}
