package ua.rd.pizza_service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.rd.pizza_service.domain.Pizza;
import ua.rd.pizza_service.repository.PizzaRepository;

@Service
public class SimplePizzaService implements PizzaService{

	private PizzaRepository pzRepozitory ;

	@Autowired
	public SimplePizzaService(PizzaRepository pizzaRepository) {	
		this.pzRepozitory = pizzaRepository;
	}
	

	@Override
	public Pizza getPizzaByID(Integer id) {
		return pzRepozitory.getPizzaByID(id);
	}

}
