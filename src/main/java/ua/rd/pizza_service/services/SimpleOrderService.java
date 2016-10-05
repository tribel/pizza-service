package ua.rd.pizza_service.services;

import java.util.ArrayList;
import java.util.List;

import ua.rd.pizza_service.domain.Customer;
import ua.rd.pizza_service.domain.Order;
import ua.rd.pizza_service.domain.Pizza;
import ua.rd.pizza_service.infrastructure.Benchmark;
import ua.rd.pizza_service.repository.OrderRepository;

public class SimpleOrderService implements OrderService{

	private PizzaService pzService;
	private OrderRepository odRepository;
	
	
	public SimpleOrderService(PizzaService pzService, OrderRepository odRepository) {
		this.pzService = pzService;
		this.odRepository = odRepository;
	}

	@Benchmark
	@Override
	public Order placeNewOrder(Customer customer, Integer... pizzasID) {
		List<Pizza> tmpPizzas = new ArrayList<>();
		
		for(Integer id : pizzasID){
            tmpPizzas.add(getPizzaById(id));  
        }
		
		 Order newOrder = new Order(customer, tmpPizzas);
		 saveOrder(newOrder);
		return newOrder;
	}
	
	
	private Pizza getPizzaById(int id) {
		return pzService.getPizzaByID(id);
	}
	
	@Benchmark(enabled = false)
	private Order saveOrder(Order order) {
		return odRepository.saveOrder(order);
	}

	
	
}
