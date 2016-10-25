package ua.rd.pizza_service.repository;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ua.rd.pizza_service.domain.Pizza;
import ua.rd.pizza_service.domain.Pizza.PizzaType;


public class JpaPizzaRepositoryIT extends RepositoryTestConfig{

	@Autowired
	private PizzaRepository pizzaRepository;
	
	@Test
	public void testSave() {
		
		Pizza pizza = new Pizza("newTestJpaPizza", 5.5, PizzaType.VEGETERIAN);
		
		pizza = pizzaRepository.save(pizza);
		assertNotNull(pizza.getId());
	}

	@Test
	public void testFind() {
	
	}

}
