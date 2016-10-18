package ua.rd.pizza_service.services;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ua.rd.pizza_service.domain.Customer;
import ua.rd.pizza_service.domain.Order;
import ua.rd.pizza_service.domain.Pizza;
import ua.rd.pizza_service.infrastructure.Benchmark;
import ua.rd.pizza_service.repository.OrderRepository;

//@Service
public class SimpleOrderService implements OrderService{

	private PizzaService pzService;
	private OrderRepository odRepository;
	
	@Autowired
	public SimpleOrderService(PizzaService pzService, OrderRepository odRepository) {
		this.pzService = pzService;
		this.odRepository = odRepository;
	}
	

	@Benchmark(enabled = true)
	@Override
	public Order placeNewOrder(Customer customer, Integer... pizzasID) {
		
		List<Pizza> tmpPizzas = new ArrayList<>();
		
		for(Integer id : pizzasID){
            tmpPizzas.add(getPizzaById(id));  
        }
		
		 Order newOrder = createNewOrder(); 
		 newOrder.setCustomer(customer);
		 newOrder.setPizzaList(tmpPizzas);
		 
		 saveOrder(newOrder);
		return newOrder;
	}
	
	protected Order createNewOrder() {
		throw new IllegalStateException("Container can`t");
	}

	private Pizza getPizzaById(int id) {
		return pzService.getPizzaByID(id);
	}
	
	private Order saveOrder(Order order) {
		return odRepository.saveOrder(order);
	}

	@Override
	public double calculateOrderSum(Order order) {
		return  order.calculateOrderPriceWithDiscount();
	}
	
	@Override
	public double calculatePureOrderSum(Order order) {
		return order.pureOrderSum();
	}


	@Override
	public void putOrderPriceToAccumulativeCard(Order order) {
		order.putOrderPriceToAccumulativeCard();
	}

	
	
}
