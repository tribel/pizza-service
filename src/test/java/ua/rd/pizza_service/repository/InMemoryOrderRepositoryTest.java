package ua.rd.pizza_service.repository;

import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import ua.rd.pizza_service.domain.Order;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class InMemoryOrderRepositoryTest {

	OrderRepository orderRepository = new InMemoryOrderRepository();
	
	@Test
	@Ignore
	public void saveOrderTest() {
		Order order = Mockito.mock(Order.class);
		assertNotNull(orderRepository.saveOrder(order));
	}
}
