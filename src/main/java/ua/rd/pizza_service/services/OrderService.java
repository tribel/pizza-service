package ua.rd.pizza_service.services;


import java.time.LocalDate;
import java.util.List;

import ua.rd.pizza_service.domain.Customer;
import ua.rd.pizza_service.domain.discount.DiscountType;
import ua.rd.pizza_service.domain.order.Order;
import ua.rd.pizza_service.domain.order.Order.OrderStatus;


public interface OrderService {

	Order placeNewOrder(Customer customer, Integer ... pizzasID);
	
	double calculateOrderSum(Order order);
	
	double calculatePureOrderSum(Order order);

	void putOrderPriceToAccumulativeCard(Order order);
	
	List<DiscountType> getOrderDiscountList(Order order);
	
	List<Order> findAll();
	
	List<Order> findByDate(LocalDate minDate, LocalDate maxDate);
	
	Order find(Long id);
	
	List<Order> findByStatus(OrderStatus status);
	
	List<Order> findOrdersByCustomer(Long id);
	
	void markAsInProgss(Long id);
	
	void markADone(Long id);
	
	void markAsCanceled(Long id);
}
