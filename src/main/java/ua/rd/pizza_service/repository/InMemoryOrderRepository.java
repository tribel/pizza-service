package ua.rd.pizza_service.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import ua.rd.pizza_service.domain.Order;

@Repository
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
