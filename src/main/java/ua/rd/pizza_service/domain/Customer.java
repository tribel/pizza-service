package ua.rd.pizza_service.domain;

public class Customer {

	private long id;
	private String name;
	private CustomerAddress address;
	private AccumulativeCard card;

	public Customer() {
	}

	public Customer(long id, String name, CustomerAddress address, AccumulativeCard card) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.card = card;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CustomerAddress getAddress() {
		return address;
	}
	
	public void setAddress(CustomerAddress address) {
		this.address = address;
	}

	public AccumulativeCard getCard() {
		return card;
	}

	public void setCard(AccumulativeCard card) {
		this.card = card;
	}
	
	
}
