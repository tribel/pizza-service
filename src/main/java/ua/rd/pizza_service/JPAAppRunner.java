package ua.rd.pizza_service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import ua.rd.pizza_service.domain.Pizza;
import ua.rd.pizza_service.domain.Pizza.PizzaType;

public class JPAAppRunner {


	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();  
		
		Pizza pizza = new Pizza(2, "Bawarian", 5.5, PizzaType.MEAT);
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(pizza);
		et.commit();
		em.clear();
		
		Pizza pizza2 = em.find(Pizza.class, 2);
		System.out.println(pizza == pizza2);
		System.out.println(pizza2);
		
		em.close();
		emf.close();
	}
}
