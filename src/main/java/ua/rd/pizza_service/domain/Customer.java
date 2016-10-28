package ua.rd.pizza_service.domain;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String name;
	
	@JoinColumn(name = "adress_id")
	@OneToOne(cascade = CascadeType.ALL)
	private CustomerAddress address;
	
	@JoinColumn(name = "card_id")
	@OneToOne(cascade = CascadeType.ALL)
	private AccumulativeCard card;

	@Enumerated(EnumType.STRING)
	private CustomerStatus status;
	
	public enum CustomerStatus {
		ACTIVE, DELETED, BANNED; 
	}
	
	public Customer() {
	}


	public Customer(Long id, String name, CustomerAddress address, AccumulativeCard card) {
		this(name, address, card);
		this.id = id;
		
	}

	public Customer(String name, CustomerAddress address, AccumulativeCard card) {
		super();
		this.name = name;
		this.address = address;
		this.card = card;
		this.status = CustomerStatus.ACTIVE;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
	
	public CustomerStatus getStatus() {
		return status;
	}
	
	public void setStatus(CustomerStatus status) {
		this.status = status;
	}

	
	
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", address=" + address + ", card=" + card + ", status="
				+ status + "]";
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
