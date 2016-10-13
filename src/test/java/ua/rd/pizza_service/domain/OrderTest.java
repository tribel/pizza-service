package ua.rd.pizza_service.domain;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ua.rd.pizza_service.domain.AccumulativeCard;
import ua.rd.pizza_service.domain.Customer;
import ua.rd.pizza_service.domain.CustomerAddress;
import ua.rd.pizza_service.domain.Order;
import ua.rd.pizza_service.domain.Pizza;
import ua.rd.pizza_service.domain.Pizza.PizzaType;
import ua.rd.pizza_service.domain.discount.AccumulativeCardDiscount;
import ua.rd.pizza_service.domain.discount.DiscountType;
import ua.rd.pizza_service.domain.discount.MaxPricePizzaDiscount;

public class OrderTest {

	List<DiscountType> discountList = new ArrayList<>();
	{
		discountList.add(new AccumulativeCardDiscount());
		discountList.add(new MaxPricePizzaDiscount());
	}
	
	List<Pizza> pizzaList = new ArrayList<>();
	{
		pizzaList.add(new Pizza(0, "name1", 3.5, PizzaType.MEAT));
		pizzaList.add(new Pizza(1, "name2", 2.0, PizzaType.SEA));
		pizzaList.add(new Pizza(2, "name3", 4.0, PizzaType.VEGETERIAN));
	}
	
	@Test
	public void calculateOrderSumPriceTest() {
		Order order = new Order(new Customer(), pizzaList);
		order.setDiscountList(discountList);
		
		assertEquals(9.5, order.calculateOrderPriceWithDiscount(), 0.01);
	
		
	}
	
	@Test
	public void calculateOrderSumPriceDiscontTest() {
		Order order = new Order(new Customer(), pizzaList);
		order.setDiscountList(discountList);
		
		pizzaList.add(new Pizza(3, "name4", 2.0, PizzaType.MEAT));
		pizzaList.add(new Pizza(5, "name4", 6.0, PizzaType.MEAT));
		
		assertEquals(15.7, order.calculateOrderPriceWithDiscount(), 0.01);
	}
	
	@Test
	public void calculateOrderSumWithAccumulativeCard() {
		Customer customer = new Customer(0, "name1", new CustomerAddress(), new AccumulativeCard());
		Order order = new Order(customer, pizzaList);
		order.setDiscountList(discountList);
		order.putOrderPriceToAccumulativeCard();
		
		List<Pizza> tmpPizzaList = new ArrayList<>();
		tmpPizzaList.add(new Pizza(0, "newName1", 10.0, PizzaType.SEA));
		tmpPizzaList.add(new Pizza(1, "newName2", 15.0, PizzaType.SEA));
		Order nextOrder = new Order(customer, tmpPizzaList);
		nextOrder.setDiscountList(discountList);
		//9.5 on accumulative card
		//25 - new order price 
		// so 25 - 9.5 * 0.1 = 24.05
		assertEquals(nextOrder.calculateOrderPriceWithDiscount(), 24.05, 0.01);
	}
	
	@Test
	public void calculateOrderSumWithAccumulativeCardSecondCase() {

		Customer customer = new Customer(0, "customer", new CustomerAddress(), new AccumulativeCard());
		List<Pizza> tmpPizzaList = new ArrayList<>();
		tmpPizzaList.add(new Pizza(0, "name1", 15.0, PizzaType.MEAT));
		tmpPizzaList.add(new Pizza(1, "name2", 25.0, PizzaType.MEAT));
		
		Order order = new Order(customer, tmpPizzaList);
		order.setDiscountList(discountList);
		order.putOrderPriceToAccumulativeCard();
		
		
		tmpPizzaList.clear();
		tmpPizzaList.add(new Pizza(4, "name", 5.0, PizzaType.VEGETERIAN));
		Order newOrder = new Order(customer, tmpPizzaList);
		newOrder.setDiscountList(discountList);
		
		
		assertEquals(newOrder.calculateOrderPriceWithDiscount(), 3.5, 0.01);
		
	}
	

}
