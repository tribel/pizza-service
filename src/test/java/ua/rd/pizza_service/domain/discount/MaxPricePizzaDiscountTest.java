package ua.rd.pizza_service.domain.discount;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ua.rd.pizza_service.domain.Pizza;
import ua.rd.pizza_service.domain.order.Order;

public class MaxPricePizzaDiscountTest {

	DiscountType discount = new MaxPricePizzaDiscount();
	
	@Test
	public void testDoDiscount() {
		List<Pizza> testPizzaList = spy(new ArrayList<>());
		Pizza pizza = mock(Pizza.class);
		when(pizza.getPrice()).thenReturn(10.0).thenReturn(15.0);
		
		testPizzaList.add(pizza);
		
		Order testOrder = mock(Order.class);
		when(testOrder.getPizzaList()).thenReturn(testPizzaList);
		
		assertEquals(3.0, discount.doDiscount(testOrder), 0.01);
		assertEquals(4.5, discount.doDiscount(testOrder), 0.01);
	}

	@Test
	public void testIsАpplicable() {
		List<Pizza> testPizzaList = spy(new ArrayList<>());
		when(testPizzaList.size()).thenReturn(5).thenReturn(4).thenReturn(2);
		
		Order testOrder = mock(Order.class);
		when(testOrder.getPizzaList()).thenReturn(testPizzaList);
		
		assertTrue(discount.isАpplicable(testOrder));
		assertFalse(discount.isАpplicable(testOrder));
		assertFalse(discount.isАpplicable(testOrder));
	}

}
