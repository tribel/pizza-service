package ua.rd.pizza_service.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class CustomerAddress {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String sreet;
	private String building;
	private Integer flat;
	
	@OneToOne
	private Customer customer;

	public CustomerAddress() {}

	public CustomerAddress(String sreet, String building, int flat) {
		super();
		this.sreet = sreet;
		this.building = building;
		this.flat = flat;
	}
	
	public CustomerAddress(Long id, String sreet, String building, int flat) {
		super();
		this.id = id;
		this.sreet = sreet;
		this.building = building;
		this.flat = flat;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getSreet() {
		return sreet;
	}

	public void setSreet(String sreet) {
		this.sreet = sreet;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public Integer getFlat() {
		return flat;
	}

	public void setFlat(Integer flat) {
		this.flat = flat;
	}

}
