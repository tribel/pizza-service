package ua.rd.pizza_service.domain.discount;

import org.springframework.stereotype.Component;

import ua.rd.pizza_service.domain.order.Order;

@Component
public class AccumulativeCardDiscount extends DiscountType {

	private static final double ACCUMULATIVE_MAX_CART_DISCOUNT = 0.3;
	private static final double ACCUMULATIVE_CART_DISCOUNT = 0.1;

	@Override
	public double doDiscount(Order order) {
		double defaultDiscount = order.getCustomer().getCard().getAccumulativeSum() 
								* ACCUMULATIVE_CART_DISCOUNT;

		if (defaultDiscount > (order.pureOrderSum() * ACCUMULATIVE_MAX_CART_DISCOUNT)) {
			return order.pureOrderSum() * ACCUMULATIVE_MAX_CART_DISCOUNT;
		} else {
			return defaultDiscount;
		}
	}

	@Override
	public boolean isАpplicable(Order order) {
		return order.getCustomer().getCard() != null ? true : false;
	}

}
