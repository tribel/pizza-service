package ua.rd.pizza_service.repository;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

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
		Pizza savePizza = new Pizza("testPizza1", 10.0, PizzaType.MEAT);
		saveImageToPizza(savePizza, 
				"C:\\Users\\Artem_Trybel\\workspace\\pizza-service\\images\\hawaii.png");
		repository.save(savePizza);
	
		savePizza =  new Pizza("testPizza2", 20.0, PizzaType.SEA);
		saveImageToPizza(savePizza,
				"C:\\Users\\Artem_Trybel\\workspace\\pizza-service\\images\\papperoni.png");
		
		repository.save(savePizza);
		
		savePizza =  new Pizza("testPizza3", 25.0, PizzaType.VEGETERIAN);
		saveImageToPizza(savePizza, 
				 "C:\\Users\\Artem_Trybel\\workspace\\pizza-service\\images\\texas.png");
	
		repository.save(savePizza);
		
		System.out.println("BEFORE");
		System.out.println(repository.findAll());
	}
	
	@After
	public void clear() {
		jdbcTemplate.execute("Delete From Pizza");
		System.out.println("AFTER");
		System.out.println(repository.findAll());
	}
	
	@Test
	public void testDeactivatePizza() {
		repository.deactivatePizza(1);
		repository.deactivatePizza(2);
	
		assertEquals("INACTIVE" , repository.find(1).getStatus().toString());
		assertEquals("INACTIVE" , repository.find(2).getStatus().toString());
	}

	@Test
	public void testActivatePizza() {
	}

	@Test 
	public void testFindAll() {
		List<Pizza> resultList = repository.findAll();
		
		assertNotNull(resultList);
		assertEquals(resultList.size(), 3);
	}

	@Test 
	public void testFindActivePizzaList() {
	}

	@Test 
	public void testFindDeactivePizzaList() {
	}

	@Test 
	public void testFindByCatogory() {
	}

	@Test 
	public void testFindByPrice() {
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
