package ua.rd.pizza_service.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ua.rd.pizza_service.domain.Pizza;
import ua.rd.pizza_service.domain.Pizza.PizzaStatus;
import ua.rd.pizza_service.domain.Pizza.PizzaType;

@Repository("pizzaRepository")
public class JpaPizzaRepository implements PizzaRepository{

	private static final String SELECT_PIZZA_BY_STATUS = "SELECT p FROM Pizza p WHERE p.status = ?sts";
	
	
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
	public void update(Pizza pizza) {
		em.merge(pizza);
	}
	
	@Override
	public void deactivatePizza(Integer id) {
		em.find(Pizza.class, id).setStatus(PizzaStatus.INACTIVE);
	}

	@Override
	public void activatePizza(Integer id) {
		em.find(Pizza.class, id).setStatus(PizzaStatus.ACTIVE);
	}

	@Override
	public List<Pizza> findAll() {
		return em.createQuery("Select p FROM Pizza p", Pizza.class).getResultList();
	}

	@Override
	public List<Pizza> findActivePizzaList() {
		List<Pizza> pizzas = em.createQuery(SELECT_PIZZA_BY_STATUS, 
				Pizza.class)
				.setParameter("sts", PizzaStatus.ACTIVE)
				.getResultList();
		
		return pizzas;
	}

	@Override
	public List<Pizza> findDeactivePizzaList() {
		List<Pizza> pizzas = em.createQuery(SELECT_PIZZA_BY_STATUS, 
				Pizza.class)
				.setParameter("sts", PizzaStatus.INACTIVE)
				.getResultList();
				
 		return pizzas;
	}

	@Override
	public List<Pizza> findByCatogory(PizzaType category) {
		List<Pizza> pizzas = em.createQuery("SELECT p FROM Pizza p WHERE p.pizzaType = ?pzTp", 
				Pizza.class)
				.setParameter("pzTp", category)
				.getResultList();
		
		return pizzas;
	}

	@Override
	public List<Pizza> findByPrice(Double minPrice, Double maxPrice) {
		List<Pizza> pizzas = em.createQuery("SELECT p FROM Pizza p WHERE p.price BETWEEN ?min AND ?max", 
				Pizza.class)
				.setParameter("min", minPrice)
				.setParameter("max", maxPrice)
				.getResultList();
		
		return pizzas;
	}

}
