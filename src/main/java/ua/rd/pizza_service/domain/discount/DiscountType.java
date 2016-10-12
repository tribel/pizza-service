package ua.rd.pizza_service.domain.discount;

import ua.rd.pizza_service.domain.Order;

public abstract class DiscountType {

	public abstract double doDiscount(Order order);
	
	public abstract boolean isАpplicable(Order order);
}
