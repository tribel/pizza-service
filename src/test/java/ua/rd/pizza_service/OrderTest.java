package ua.rd.pizza_service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ua.rd.pizza_service.domain.Order;
import ua.rd.pizza_service.domain.Pizza;
import ua.rd.pizza_service.domain.Pizza.PizzaType;

public class OrderTest {

	List<Pizza> pizzaList = new ArrayList<>();
	{
		pizzaList.add(new Pizza(0, "name1", 3.5, PizzaType.MEAT));
		pizzaList.add(new Pizza(1, "name2", 2.0, PizzaType.SEA));
		pizzaList.add(new Pizza(2, "name3", 4.0, PizzaType.VEGETERIAN));
	}
	
	@Test
	public void calculateOrderSumPriceTest() {
		Order order = new Order(null, pizzaList);
		assertEquals(9.5, order.calculateOrderSumPrice(), 0.01);
	
		
	}
	
	@Test
	public void calculateOrderSumPriceDiscontTest() {
		Order order = new Order(null, pizzaList);
		pizzaList.add(new Pizza(3, "name4", 2.0, PizzaType.MEAT));
		pizzaList.add(new Pizza(5, "name4", 6.0, PizzaType.MEAT));
		
		assertEquals(15.7, order.calculateOrderSumPrice(), 0.01);
	}
	
	

}
