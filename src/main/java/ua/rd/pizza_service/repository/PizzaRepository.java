package ua.rd.pizza_service.repository;


import java.util.List;

import ua.rd.pizza_service.domain.Pizza;

public interface PizzaRepository {

	Pizza save(Pizza pizza);

	Pizza find(Integer id);
	
	void deactivatePizza(Integer id);
	
	void activatePizza(Integer id);
	
	List<Pizza> findAll();
	
	List<Pizza> findActivePizza();
	
	List<Pizza> findDeactivePizza();
	
	

}
