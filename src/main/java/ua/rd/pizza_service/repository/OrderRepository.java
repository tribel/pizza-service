package ua.rd.pizza_service.repository;

import ua.rd.pizza_service.domain.Order;

public interface OrderRepository {

	Order saveOrder(Order order);
}
