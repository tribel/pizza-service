package ua.rd.pizza_service.domain;

public class CustomerAddress {

	private String sreet;
	private String building;
	private int flat;

	public CustomerAddress() {}

	public CustomerAddress(String sreet, String building, int flat) {
		super();
		this.sreet = sreet;
		this.building = building;
		this.flat = flat;
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

	public int getFlat() {
		return flat;
	}

	public void setFlat(int flat) {
		this.flat = flat;
	}

}
