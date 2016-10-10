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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Customer other = (Customer) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}
