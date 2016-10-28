package ua.rd.pizza_service.repository;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ua.rd.pizza_service.domain.Pizza;
import ua.rd.pizza_service.domain.Pizza.PizzaType;

public class JpaPizzaRepositoryIT extends RepositoryTestConfig{

	@Autowired
	PizzaRepository repository;
	
	@Before
	public  void setUp() {
		Pizza p = new Pizza(1, "testPizza1", 10.0, PizzaType.MEAT);
		saveImageToPizza(p, 
				"C:\\Users\\Artem_Trybel\\workspace\\pizza-service\\images\\hawaii.png");
		
		nativeSave(p);
		
		p =  new Pizza(2, "testPizza2", 20.0, PizzaType.SEA);
		saveImageToPizza(p,
				"C:\\Users\\Artem_Trybel\\workspace\\pizza-service\\images\\papperoni.png");
		nativeSave(p);
		
		
		p =  new Pizza(3, "testPizza3", 25.0, PizzaType.VEGETERIAN);
		saveImageToPizza(p, 
				 "C:\\Users\\Artem_Trybel\\workspace\\pizza-service\\images\\texas.png");
	
		nativeSave(p);
	}
	
	
	@After
	public void clear() {
		jdbcTemplate.execute("Delete From Pizza");
	}
	
	@Test
	public void testDeactivatePizza() {
		repository.deactivatePizza(1);
		repository.deactivatePizza(2);

		
		assertEquals(2, repository.findDeactivePizzaList().size());
	}

	@Test
	public void testActivatePizza() {
		repository.activatePizza(1);
		repository.activatePizza(3);
		
		List<Map<String, Object>> pizzas = 
				jdbcTemplate.queryForList("SELECT * FROM Pizza p WHERE p.status = 'ACTIVE' ");
		
		System.out.println(pizzas);
		assertEquals(3, pizzas.size());
	}

	@Test 
	public void testFindAll() {
		List<Pizza> resultList = repository.findAll();
		
		assertNotNull(resultList);
		assertEquals(resultList.size(), 3);
	}

	@Test 
	public void testFindActivePizzaList() {
		jdbcTemplate.update("UPDATE Pizza SET status = 'INACTIVE' WHERE id = 1 ");

		List<Pizza> pizzas = repository.findActivePizzaList();
		assertEquals(2, pizzas.size());
	}

	@Test 
	public void testFindDeactivePizzaList() {
		jdbcTemplate.update("UPDATE Pizza SET status = 'INACTIVE' WHERE id = 1 ");
		jdbcTemplate.update("UPDATE Pizza SET status = 'INACTIVE' WHERE id = 2 ");
		
		List<Pizza> pizzas = repository.findDeactivePizzaList();
		assertEquals(2, pizzas.size());
	}

	@Test 
	public void testFindByCatogory() {
		List<Pizza> pizzas = repository.findByCatogory(PizzaType.MEAT);
		
		assertEquals(1, pizzas.size());
		assertEquals(PizzaType.MEAT, pizzas.get(0).getPizzaType());
		
		pizzas = repository.findByCatogory(PizzaType.VEGETERIAN);
		assertEquals(1, pizzas.size());
		assertEquals(PizzaType.VEGETERIAN, pizzas.get(0).getPizzaType());
	}

	@Test 
	public void testFindByPrice() {
		List<Pizza> pizzas = repository.findByPrice(15.0, 30.0);
		assertEquals(2, pizzas.size());
		
		pizzas = repository.findByPrice(5.0, 15.0);
		assertEquals(1, pizzas.size());
	}

	private void nativeSave(Pizza p) {
		jdbcTemplate.update("INSERT INTO Pizza(id , name, price, image, status, pizzaType)"
				+ " values(?, ?, ?, ?, ?, ?)", 
				p.getId(), p.getName(), p.getPrice(), p.getImage(),p.getStatus().toString(),
				p.getPizzaType().toString());
	}
	
	private void saveImageToPizza(Pizza pizza, String path) {
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
