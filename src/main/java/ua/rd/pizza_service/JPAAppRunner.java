package ua.rd.pizza_service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import ua.rd.pizza_service.domain.AccumulativeCard;
import ua.rd.pizza_service.domain.Customer;
import ua.rd.pizza_service.domain.CustomerAddress;
import ua.rd.pizza_service.domain.Order;
import ua.rd.pizza_service.domain.Pizza;
import ua.rd.pizza_service.domain.Pizza.PizzaType;

public class JPAAppRunner {


	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();  
		
		List<Pizza> pizzas = new ArrayList<>();
		pizzas.add(new Pizza("Bawarian", 5.5, PizzaType.MEAT));
		pizzas.add(new Pizza("test", 21, PizzaType.VEGETERIAN));
		pizzas.add(new Pizza("testq1", 10.5, PizzaType.SEA));
		
		Order order = new Order(new Customer("test", new CustomerAddress("test", "12", 23), 
				new AccumulativeCard()	), pizzas);
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(order);
		/*em.persist(new Pizza("Bawarian", 5.5, PizzaType.MEAT));
		em.persist(new Pizza("test", 21, PizzaType.VEGETERIAN));
		em.persist(new Pizza("testq1", 10.5, PizzaType.SEA));*/
	//	em.merge(null);
		et.commit();
	//	em.clear();
		
		//Pizza pizza2 = em.find(Pizza.class, 2);
		//System.out.println(pizza == pizza2);
		//System.out.println(pizza2);
		
		em.close();
		emf.close();
	}
}
