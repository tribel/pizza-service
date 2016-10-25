package ua.rd.pizza_service.repository;


import ua.rd.pizza_service.domain.Pizza;

public interface PizzaRepository {


	Pizza save(Pizza pizza);

	Pizza find(Integer id);

}
