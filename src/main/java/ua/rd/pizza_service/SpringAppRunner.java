package ua.rd.pizza_service;

import java.util.Arrays;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ua.rd.pizza_service.domain.Customer;
import ua.rd.pizza_service.domain.Order;
import ua.rd.pizza_service.services.OrderService;

public class SpringAppRunner {

	public static void main(String[] args) {
		
		ConfigurableApplicationContext repoContext = 
				new ClassPathXmlApplicationContext("repoContext.xml");
		
		System.out.println(Arrays.toString(repoContext.getBeanDefinitionNames()));
		
		
		ConfigurableApplicationContext appContext = 
				new ClassPathXmlApplicationContext(new String[]{"appContext.xml"}, repoContext);
		
		
		System.out.println(Arrays.toString(appContext.getBeanDefinitionNames()));
		

		OrderService orderService = (OrderService)appContext.getBean("orderService");
		//((SimpleOrderService)orderService).setContext(appContext);
		Order order = orderService.placeNewOrder(new Customer(),  2 , 3);
		
		System.out.println(order);
		
		repoContext.close();
		appContext.close();
	}
}
