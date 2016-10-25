package ua.rd.pizza_service;

import java.util.Arrays;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ua.rd.pizza_service.domain.AccumulativeCard;
import ua.rd.pizza_service.domain.Customer;
import ua.rd.pizza_service.domain.Order;
import ua.rd.pizza_service.domain.Pizza;
import ua.rd.pizza_service.domain.Pizza.PizzaType;
import ua.rd.pizza_service.repository.PizzaRepository;
import ua.rd.pizza_service.services.OrderService;

public class SpringAppRunner {

	public static void main(String[] args) {
		
		ConfigurableApplicationContext repoContext = 
				new ClassPathXmlApplicationContext("repoContext.xml");
		
		System.out.println(Arrays.toString(repoContext.getBeanDefinitionNames()));
		
		
		ConfigurableApplicationContext appContext = 
				new ClassPathXmlApplicationContext(new String[]{"appContext.xml"}, repoContext);
		
		
		System.out.println(Arrays.toString(appContext.getBeanDefinitionNames()));
		
		
		PizzaRepository pizzaRepository = (PizzaRepository)appContext.getBean("pizzaRepository");
		Pizza pizza = new Pizza("newJpaPizza", 5.5, PizzaType.VEGETERIAN);
		pizza = pizzaRepository.save(pizza);
		System.out.println(pizza);
		
		/*OrderService orderService = (OrderService)appContext.getBean("orderService");
		
		Customer customer = new Customer(1L, "name", null, new AccumulativeCard());
		Order order = orderService.placeNewOrder(customer, 0 ,1,  2 , 3, 4);
		orderService.placeNewOrder(customer, 0, 1 , 2, 3);*/
		
		

		repoContext.close();
		appContext.close();
	}
}
