package ua.rd.pizza_service.domain.discount;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import ua.rd.pizza_service.domain.AccumulativeCard;
import ua.rd.pizza_service.domain.Customer;
import ua.rd.pizza_service.domain.Order;

public class AccumulativeCardDiscountTest {

	DiscountType discount = new AccumulativeCardDiscount();
	
	@Test
	public void testDoDiscount() {
		AccumulativeCard testCard = mock(AccumulativeCard.class);
		when(testCard.getAccumulativeSum()).thenReturn(15.0).thenReturn(50.0);
		
		Customer testCustomer = mock(Customer.class);
		when(testCustomer.getCard()).thenReturn(testCard);
		
		Order testOrder = mock(Order.class);
		when(testOrder.getCustomer()).thenReturn(testCustomer);
		when(testOrder.pureOrderSum()).thenReturn(50.0).thenReturn(14.0);
		
		assertEquals(1.5, discount.doDiscount(testOrder) , 0.01);
		assertEquals(4.2, discount.doDiscount(testOrder) , 0.01);
	}

	@Test
	public void testIsАpplicable() {
		Customer testCustomer = mock(Customer.class);
		when(testCustomer.getCard()).thenReturn(new AccumulativeCard()).thenReturn(null);
		
		Order testOrder = mock(Order.class);
		when(testOrder.getCustomer()).thenReturn(testCustomer);
		
		assertTrue(discount.isАpplicable(testOrder));
		assertFalse(discount.isАpplicable(testOrder));
		
	}

}
