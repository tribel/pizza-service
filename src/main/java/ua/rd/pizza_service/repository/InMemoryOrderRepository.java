package ua.rd.pizza_service.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import ua.rd.pizza_service.domain.Order;
import ua.rd.pizza_service.domain.Order.Status;

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

	@Override
	public List<Order> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> findByDate(LocalDate minDate, LocalDate maxDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order find(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> findByStatus(Status status) {
		// TODO Auto-generated method stub
		return null;
	}


	
	
}
