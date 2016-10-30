package ua.rd.pizza_service.domain.discount;

import org.springframework.stereotype.Component;

import ua.rd.pizza_service.domain.Pizza;
import ua.rd.pizza_service.domain.order.Order;

@Component
public class MaxPricePizzaDiscount extends DiscountType{

	private static final double PIZZA_DISCOUNT = 0.3;
	
	@Override
	public double doDiscount(Order order) {
		return  order.getPizzaList().stream()
				.mapToDouble(Pizza::getPrice)
				.max().getAsDouble() * PIZZA_DISCOUNT;
	}

	@Override
	public boolean isАpplicable(Order order) {
		return order.getPizzaList().size() > 4 ? true: false;
			
	}

}
