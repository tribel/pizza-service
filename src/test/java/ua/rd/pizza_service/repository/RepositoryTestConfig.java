package ua.rd.pizza_service.repository;

import java.io.File;
import java.io.FileInputStream;

import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ua.rd.pizza_service.domain.AccumulativeCard;
import ua.rd.pizza_service.domain.Customer;
import ua.rd.pizza_service.domain.CustomerAddress;
import ua.rd.pizza_service.domain.Pizza;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:inMemoryRepoContextH2.xml")
@Rollback(false)
public class RepositoryTestConfig extends AbstractTransactionalJUnit4SpringContextTests{

	
	
	protected void nativePizzaSave(Pizza p) {
		jdbcTemplate.update("INSERT INTO Pizza(id , name, price, image, status, pizzaType)"
				+ " values(?, ?, ?, ?, ?, ?)", 
				p.getId(), p.getName(), p.getPrice(), p.getImage(),p.getStatus().toString(),
				p.getPizzaType().toString());
	}
	
	protected void saveCustomer(Customer c, CustomerAddress ca, AccumulativeCard card) {
		
		jdbcTemplate.update("INSERT INTO CustomerAddress (id, sreet, building, flat) "
				+ "values(?, ?, ?, ?)" ,
				ca.getId(), ca.getSreet(), ca.getBuilding(), ca.getFlat());
		
		jdbcTemplate.update("INSERT INTO AccumulativeCard (id, accumulativeSum) "
				+ "values(?, ?)" ,
				card.getId(), card.getAccumulativeSum());
		
		jdbcTemplate.update("INSERT INTO Customer (id, name, adress_id, card_id, status) "
				+ "values(?, ?, ?, ?, ?)", 
				c.getId(), c.getName(), ca.getId(), card.getId(), c.getStatus().toString());	
	
		
	}
	
	protected void saveImageToPizza(Pizza pizza, String path) {
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
