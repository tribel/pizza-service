package ua.rd.pizza_service.domain;

public class Pizza {

	private int id;
	private String name;
	private double price;
	private PizzaType pizzaType;

	public enum PizzaType {
		VEGETERIAN, SEA, MEAT;
	}
	
	public Pizza() {}

	public Pizza(int id, String name, double price, PizzaType pizzaType) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.pizzaType = pizzaType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	
	public PizzaType getPizzaType() {
		return pizzaType;
	}

	public void setPizzaType(PizzaType pizzaType) {
		this.pizzaType = pizzaType;
	}

	@Override
	public String toString() {
		return "Pizza [id=" + id + ", name=" + name + ", price=" + price + ", pizzaType=" + pizzaType + "]";
	}

	

	
}
