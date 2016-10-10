package ua.rd.pizza_service.domain;

import java.util.List;

public class Order {

	private static final double ACCUMULATIVE_CART_DISCOUNT = 0.1;

	private static final double PIZZA_DISCOUNT = 0.3;
	
	private long id;
	private Customer customer;
	private List<Pizza> pizzaList;
	private Status status;
	
	public enum Status {
		NEW, IN_PROGRSS, DONE, CANCELED;
		
	}

	public Order() {
	}

	public Order(Customer customer, List<Pizza> pizzaList) {
		super();
		this.customer = customer;
		this.pizzaList = pizzaList;
		this.status = Status.NEW;
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
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}

	public void orderCancel() {
		this.status = Status.CANCELED;
	}
	
	public void putOrderPriceToAccumulativeCard() {
		if(isAccumulativeCartExist())
			this.customer.getCard().setAccumulativeSum(this.calculateOrderSumPrice());
	}
	

	public double calculateOrderSumPrice() {
		double resultPrice = 0.0;
		
		if(pizzaList.size() > 4) {
			double discountAmount = pizzaList.stream()
								.mapToDouble(Pizza::getPrice)
								.max().getAsDouble() * PIZZA_DISCOUNT;
			
			resultPrice = pureOrderSum() - discountAmount;
		} else {
			resultPrice = pureOrderSum();
		}
		
		if(isAccumulativeCartExist()) 
			return resultPrice - accumulativeCardDiscont(resultPrice);
		else 
			return resultPrice;
	}
	
	private boolean isAccumulativeCartExist() {
		return (customer.getCard() != null) ? true : false;
	}
	
	private double accumulativeCardDiscont(double sum) {
		double defaultDiscount = customer.getCard().getAccumulativeSum() * ACCUMULATIVE_CART_DISCOUNT;
		
		if(defaultDiscount > (sum * 0.3)) {
			return sum * 0.3;
		} else {
			return defaultDiscount;
		}
	}
	
	private double pureOrderSum() {
		return pizzaList.stream().mapToDouble(Pizza::getPrice).sum();
	}
	
	@Override
	public String toString() {
		return "Order [id=" + id + ", customer=" + customer + ", pizzaList=" + pizzaList + "]";
	}
	
	

}
