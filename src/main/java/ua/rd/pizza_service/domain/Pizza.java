package ua.rd.pizza_service.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Pizza {

	@Id
	@SequenceGenerator(allocationSize = 10, name = "PizzaSEQ")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "PizzaSEQ")
	private int id;
	private String name;
	private double price;
	
	@Enumerated(EnumType.STRING)
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
	
	public Pizza(String name, double price, PizzaType pizzaType) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pizzaType == null) ? 0 : pizzaType.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pizza other = (Pizza) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pizzaType != other.pizzaType)
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		return true;
	}

	

	
}
