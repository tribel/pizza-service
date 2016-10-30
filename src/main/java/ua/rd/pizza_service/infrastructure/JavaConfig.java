package ua.rd.pizza_service.infrastructure;

import java.util.HashMap;
import java.util.Map;


import ua.rd.pizza_service.services.SimpleOrderService;
import ua.rd.pizza_service.services.SimplePizzaService;

public class JavaConfig implements Config {

	Map<String, Class<?>> classes = new HashMap<>();
	
	{
		//classes.put("pizzaRepository", InMemoryPizzaRepository.class);
		//classes.put("orderRepository", InMemoryOrderRepository.class);
		classes.put("pizzaService", SimplePizzaService.class);
		classes.put("orderService", SimpleOrderService.class);
		
		
	}
	
	
	@Override
	public Class<?> getImpl(String name) {
		return classes.get(name);
	}

	
}
