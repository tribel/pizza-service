package ua.rd.pizza_service.service;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import ua.rd.pizza_service.domain.Pizza;
import ua.rd.pizza_service.domain.Pizza.PizzaType;
import ua.rd.pizza_service.repository.PizzaRepository;
import ua.rd.pizza_service.services.PizzaService;
import ua.rd.pizza_service.services.SimplePizzaService;

public class SimplePizzaServiceTest {

	PizzaService pizzaService;
	
	@Test
	public void test() {
		PizzaRepository repository = mock(PizzaRepository.class);
		when(repository.getPizzaByID(anyInt())).thenReturn(new Pizza(1, "test", 10.0, PizzaType.MEAT));
		
		pizzaService = new SimplePizzaService(repository);
		assertEquals(new Pizza(1, "test", 10.0, PizzaType.MEAT), pizzaService.getPizzaByID(10));

	}

}
