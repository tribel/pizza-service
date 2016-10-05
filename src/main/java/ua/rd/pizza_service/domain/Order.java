package ua.rd.pizza_service.domain;

import java.util.List;

public class Order {

	private long id;
	private Customer customer;
	private List<Pizza> pizzaList;

	public Order() {
	}

	public Order(Customer customer, List<Pizza> pizzaList) {
		super();
		this.customer = customer;
		this.pizzaList = pizzaList;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Pizza> getPizzaList() {
		return pizzaList;
	}

	public void setPizzaList(List<Pizza> pizzaList) {
		this.pizzaList = pizzaList;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void addPizza(Pizza pizza) {
		pizzaList.add(pizza);
	}
	
	@Override
	public String toString() {
		return "Order [id=" + id + ", customer=" + customer + ", pizzaList=" + pizzaList + "]";
	}
	
	

}
