package ua.rd.pizza_service.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ua.rd.pizza_service.domain.Pizza;
import ua.rd.pizza_service.domain.Pizza.PizzaType;


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

	@Override
	public void deactivatePizza(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void activatePizza(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Pizza> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pizza> findActivePizzaList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pizza> findDeactivePizzaList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pizza> findByCatogory(PizzaType category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pizza> findByPrice(Double minPrice, Double maxPrice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Pizza pizza) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
