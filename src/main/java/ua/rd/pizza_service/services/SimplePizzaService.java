package ua.rd.pizza_service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.rd.pizza_service.domain.Pizza;
import ua.rd.pizza_service.domain.Pizza.PizzaType;
import ua.rd.pizza_service.repository.PizzaRepository;

@Service
public class SimplePizzaService implements PizzaService{

	private PizzaRepository pzRepozitory ;

	@Autowired
	public SimplePizzaService(PizzaRepository pizzaRepository) {	
		this.pzRepozitory = pizzaRepository;
	}

	@Override
	@Transactional
	public Pizza save(Pizza pizza) {
		return pzRepozitory.save(pizza);
	}

	@Override
	public Pizza find(Integer id) {
		return pzRepozitory.find(id);
	}

	@Override
	@Transactional
	public void update(Pizza pizza) {
		pzRepozitory.update(pizza);
	}

	@Override
	@Transactional
	public void deactivatePizza(Integer id) {
		pzRepozitory.deactivatePizza(id);
	}

	@Override
	@Transactional
	public void activatePizza(Integer id) {
		pzRepozitory.activatePizza(id);
	}

	@Override
	public List<Pizza> findAll() {
		return pzRepozitory.findAll();
	}

	@Override
	public List<Pizza> findActivePizzaList() {
		return pzRepozitory.findActivePizzaList();
	}

	@Override
	public List<Pizza> findDeactivePizzaList() {
		return pzRepozitory.findDeactivePizzaList();
	}

	@Override
	public List<Pizza> findByCatogory(PizzaType category) {
		return pzRepozitory.findByCatogory(category);
	}

	@Override
	public List<Pizza> findByPrice(Double minPrice, Double maxPrice) {
		return pzRepozitory.findByPrice(minPrice, maxPrice);
	}
	



}
