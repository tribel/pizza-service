package ua.rd.pizza_service.services;


import java.util.List;

import ua.rd.pizza_service.domain.Pizza;
import ua.rd.pizza_service.domain.Pizza.PizzaType;


public interface PizzaService {

	Pizza save(Pizza pizza);
	
	 Pizza find(Integer id);
	
	 void update(Pizza pizza);
	 
	 void deactivatePizza(Integer id);
	 
	 void activatePizza(Integer id);
	 
	 List<Pizza> findAll();
	 
	 List<Pizza> findActivePizzaList();
	 
	 List<Pizza> findDeactivePizzaList();
	 
	 List<Pizza> findByCatogory(PizzaType category);
	 
	 List<Pizza> findByPrice(Double minPrice, Double maxPrice);
	
}
