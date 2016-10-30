package ua.rd.pizza_service.repository;


import java.time.LocalDate;
import java.util.List;

import ua.rd.pizza_service.domain.order.Order;
import ua.rd.pizza_service.domain.order.Order.OrderStatus;


public interface OrderRepository {

	Order saveOrder(Order order);
	
	List<Order> findAll();
	
	List<Order> findByDate(LocalDate minDate, LocalDate maxDate);
	
	Order find(Long id);
	
	List<Order> findByStatus(OrderStatus status);
	
	List<Order> findOrdersByCustomer(Long id);
	
	void markAsInProgss(Long id);
	
	void markADone(Long id);
	
	void markAsCanceled(Long id);
	
}
