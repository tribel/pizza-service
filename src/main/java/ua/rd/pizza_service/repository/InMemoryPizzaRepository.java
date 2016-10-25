package ua.rd.pizza_service.repository;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ua.rd.pizza_service.domain.Pizza;

@Repository
public class InMemoryPizzaRepository implements PizzaRepository{

	
	private Map<Integer, Pizza> pizzas = new HashMap<>();
	
	public InMemoryPizzaRepository() {
	}

	//@PostCreate
	@PostConstruct	
	public void init() {
		
		pizzas.put(0,  new Pizza(0, "name1", 1.5, Pizza.PizzaType.MEAT));
		pizzas.put(1,  new Pizza(1, "name2", 2.5, Pizza.PizzaType.SEA));
		pizzas.put(2,  new Pizza(2, "name3", 3.5, Pizza.PizzaType.VEGETERIAN));
		pizzas.put(3,  new Pizza(3, "name4", 5.0, Pizza.PizzaType.VEGETERIAN));
		pizzas.put(4,  new Pizza(4, "name5", 6.0, Pizza.PizzaType.VEGETERIAN));
	}




	public Pizza save(Pizza pizza) {
		throw new UnsupportedOperationException("jpa not supported!!!!");
	}

	@Override
	public Pizza find(Integer id) {
		return pizzas.get(id);
		
	}
	
	
	
}
