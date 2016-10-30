package ua.rd.pizza_service.repository;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ua.rd.pizza_service.domain.AccumulativeCard;
import ua.rd.pizza_service.domain.Customer;
import ua.rd.pizza_service.domain.CustomerAddress;
import ua.rd.pizza_service.domain.Pizza;
import ua.rd.pizza_service.domain.Pizza.PizzaType;
import ua.rd.pizza_service.domain.discount.AccumulativeCardDiscount;
import ua.rd.pizza_service.domain.discount.DiscountType;
import ua.rd.pizza_service.domain.discount.MaxPricePizzaDiscount;
import ua.rd.pizza_service.domain.order.Order;

public class JpaOrderRepositoryIT extends RepositoryTestConfig{

	@Autowired
	private OrderRepository repository;
	
	private List<DiscountType> discountList;
	
	@Before
	public void setUp() {
		discountList = new ArrayList<>();
		discountList.add(new AccumulativeCardDiscount());
		discountList.add(new MaxPricePizzaDiscount());
		
		nativePizzaSave(new Pizza(1, "name1", 10.0, PizzaType.MEAT));
		nativePizzaSave(new Pizza(2, "name2", 20.0, PizzaType.VEGETERIAN));
		nativePizzaSave(new Pizza(3, "name3", 30.0, PizzaType.SEA));
	
		CustomerAddress address = new CustomerAddress(1L, "srt1", "1", 1);
		AccumulativeCard card = new AccumulativeCard(1L, 0);
		Customer customer = new Customer(1L, "cust1", address, card);
		saveCustomer(customer, address, card);
		saveOrder(new Order(1L, customer, discountList));
	
		address = new CustomerAddress(2L, "str2", "2", 2);
		card = new AccumulativeCard(2L, 0);
		customer = new Customer(2L, "cust3", address, card);
		saveCustomer(customer, address, card);
		saveOrder(new Order(2L, customer, discountList));
		
		address = new CustomerAddress(3L, "str3", "3", 3);
		card = new AccumulativeCard(3L, 0);
		customer = new Customer(3L, "cust3", address, card);
		saveCustomer(customer, address, card);
		saveOrder(new Order(3L, customer, discountList));
	}
	
	@After
	public void clear() {
		jdbcTemplate.update("DELETE FROM orders_pizzas");
		jdbcTemplate.update("DELETE FROM Orders");
	}
	
	public void saveOrder(Order order) {
		jdbcTemplate.update("INSERT INTO Orders(id, customer_id, date, status)"
				+ "values(?, ?, ?, ?)",
				order.getId(), order.getCustomer().getId(), order.getDate(), order.getStatus().toString());
	
		for(Pizza p: order.getPizzaList()) {
			jdbcTemplate.update("INSERT INTO orders_pizzas(order_id, pizza_id, pizza_count)"
					+ "values(?, ?, ?)",
					order.getId(), p.getId(), 1);
		}
	
	}
	
	@Test
	public void testFindAll() {
		List<Order> orders = repository.findAll();
		assertEquals(3, orders.size());
	}

	@Test
	public void testFindByDate() {
	}

	@Test
	public void testFindByStatus() {
	}

	@Test
	public void testFindOrdersByCustomer() {
	}

	@Test
	public void testMarkAsInProgss() {
	}

	@Test
	public void testMarkADone() {
	}

	@Test
	public void testMarkAsCanceled() {
	}

}
