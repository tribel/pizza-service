package ua.rd.pizza_service.service;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ua.rd.pizza_service.domain.Customer;
import ua.rd.pizza_service.domain.Order;
import ua.rd.pizza_service.domain.Pizza;
import ua.rd.pizza_service.domain.Pizza.PizzaType;
import ua.rd.pizza_service.repository.OrderRepository;
import ua.rd.pizza_service.services.PizzaService;
import ua.rd.pizza_service.services.SimpleOrderService;

public class SimpleOrderServiceTest {

	SimpleOrderService orderService;
	PizzaService pizzaService;
	OrderRepository orderRepository;

	
	@Before
	public void initTest() {
		pizzaService = mock(PizzaService.class);
		orderRepository = mock(OrderRepository.class);

	}
	
	@Test
	public void testPlaceNewOrder() {

		
		when(pizzaService.getPizzaByID(anyInt())).thenReturn(new Pizza(0, "name", 10.0, PizzaType.MEAT))
												 .thenReturn(new Pizza(1, "name1", 15.0, PizzaType.SEA));
	

		orderService = spy(orderService = new SimpleOrderService(pizzaService, orderRepository));

		
		
		
		Order tmpOrder = orderService.placeNewOrder(new Customer(1L, "name", null, null), 1 , 2);
		List<Pizza> assertList = new ArrayList<>();
		assertList.add(new Pizza(0, "name", 10.0, PizzaType.MEAT));
		assertList.add(new Pizza(1, "name1", 15.0, PizzaType.SEA));
		
		assertEquals(tmpOrder.getPizzaList(), assertList);
		assertEquals(tmpOrder.getCustomer(), new Customer(1L, "name", null, null));
		
	}

}
