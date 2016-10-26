package ua.rd.pizza_service.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ua.rd.pizza_service.domain.Pizza;

@Repository("pizzaRepository")
public class JpaPizzaRepository implements PizzaRepository{


	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional
	public Pizza save(Pizza pizza) {
		Pizza newPizza = em.merge(pizza);
		return newPizza;
	}

	@Override
	public Pizza find(Integer id) {
		return em.find(Pizza.class, id);
		
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
	public List<Pizza> findActivePizza() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pizza> findDeactivePizza() {
		// TODO Auto-generated method stub
		return null;
	}
}
