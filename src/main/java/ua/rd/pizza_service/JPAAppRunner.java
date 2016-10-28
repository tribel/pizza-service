package ua.rd.pizza_service;

import java.io.File;
import java.io.FileInputStream;
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
		Pizza pizza1 = new Pizza("Bawarian", 5.5, PizzaType.MEAT);
		saveImageToPizza(pizza1, "C:\\Users\\Artem_Trybel\\workspace\\pizza-service\\images\\hawaii.png");
		Pizza pizza2 = new Pizza("test", 21, PizzaType.VEGETERIAN);
		saveImageToPizza(pizza2, "C:\\Users\\Artem_Trybel\\workspace\\pizza-service\\images\\papperoni.png");
		Pizza pizza3 = new Pizza("testq1", 10.5, PizzaType.SEA);
		saveImageToPizza(pizza3, "C:\\Users\\Artem_Trybel\\workspace\\pizza-service\\images\\texas.png");
		pizza3.setId(3);
		pizzas.add(pizza1);
		pizzas.add(pizza2);
		pizzas.add(pizza3);
		
		//Order order = new Order(new Customer("test", new CustomerAddress("test", "12", 23), 
		//		new AccumulativeCard()	), pizzas);
		
		EntityTransaction et = em.getTransaction();
		et.begin();
		//em.persist(pizza1);
	//	em.persist(pizza2);
		//em.merge(pizza3);
		//em.persist(order);
		//em.persist(new Customer("customer1", new CustomerAddress("street1", "22", 65), new AccumulativeCard()));
		//System.out.println(em.find(Pizza.class, 140));
		et.commit();
	//	em.clear();
		

		
		em.close();
		emf.close();
	}
	
	public static void saveImageToPizza(Pizza pizza, String path) {

		File file = new File(path);
		byte[] bFile = new byte[(int) file.length()];

		try {

			FileInputStream fileInputStream = new FileInputStream(file);
			fileInputStream.read(bFile);
			fileInputStream.close();

		} catch (Exception e) {

			e.printStackTrace();

		}
		
		pizza.setImage(bFile);

	}
}
