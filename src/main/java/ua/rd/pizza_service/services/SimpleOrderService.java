package ua.rd.pizza_service.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import ua.rd.pizza_service.domain.Customer;
import ua.rd.pizza_service.domain.Order;
import ua.rd.pizza_service.domain.Pizza;
import ua.rd.pizza_service.infrastructure.Benchmark;
import ua.rd.pizza_service.repository.OrderRepository;

//@Service
public class SimpleOrderService implements OrderService, ApplicationContextAware{

	private PizzaService pzService;
	private OrderRepository odRepository;
	private ApplicationContext context;
	
	@Autowired
	public SimpleOrderService(PizzaService pzService, OrderRepository odRepository) {
		this.pzService = pzService;
		this.odRepository = odRepository;
	}
	
	@Override
	public void setApplicationContext(ApplicationContext context) {
		this.context = context;
	}

	@Benchmark
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
		 calculateOrderSum(newOrder);
		return newOrder;
	}
	
	@Benchmark
	protected Order createNewOrder() {
		return (Order)context.getBean("order");
		//throw new IllegalStateException("Container can`t");
	}

	private Pizza getPizzaById(int id) {
		return pzService.getPizzaByID(id);
	}
	
	private Order saveOrder(Order order) {
		return odRepository.saveOrder(order);
	}

	@Benchmark
	@Override
	public double calculateOrderSum(Order order) {
		return order.calculateOrderSumPrice();
	}

	
	
}
