package ua.rd.pizza_service.repository;


import java.time.LocalDate;
import java.util.List;

import ua.rd.pizza_service.domain.Order;


public interface OrderRepository {

	Order saveOrder(Order order);
	
	List<Order> findAll();
	
	List<Order> findByDate(LocalDate minDate, LocalDate maxDate);
	
	Order find(Integer id);
	
	List<Order> findByStatus(Order.Status status);
	
	
}
