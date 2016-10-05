package ua.rd.pizza_service;

import ua.rd.pizza_service.domain.Customer;
import ua.rd.pizza_service.domain.Order;
import ua.rd.pizza_service.infrastructure.ApplicationContext;
import ua.rd.pizza_service.infrastructure.Context;
import ua.rd.pizza_service.infrastructure.JavaConfig;
import ua.rd.pizza_service.services.OrderService;

public class AppRun {

	public static void main(String[] args) {
		
/*		Customer customer = null;        
        Order order;
        
        OrderService orderService = new SimpleOrderService();
        
       
        
        System.out.println(order);*/
		
/*		
		PizzaService pizzaService = new SimplePizzaService();
        System.out.println(pizzaService.getPizzaByID(1));*/
        Context context = new ApplicationContext(new JavaConfig());
        
       // PizzaRepository pizzaRepository = context.getBean("pizzaRepository");
       // System.out.println(pizzaRepository.getPizzaByID(1));
        
        
        Customer customer = null;
        OrderService orderService = context.getBean("orderService");
        Order order = orderService.placeNewOrder(customer, 1 , 3);
        System.out.println(order);
	}
}
