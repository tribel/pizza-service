package ua.rd.pizza_service.services;

import ua.rd.pizza_service.domain.Pizza;
import ua.rd.pizza_service.repository.PizzaRepository;

public class SimplePizzaService implements PizzaService{

	private PizzaRepository pzRepozitory ;
	
	public SimplePizzaService(PizzaRepository pizzaRepository) {	
		this.pzRepozitory = pizzaRepository;
	}
	

	@Override
	public Pizza getPizzaByID(Integer id) {
		return pzRepozitory.getPizzaByID(id);
	}

}
