package ua.rd.pizza_service.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class AccumulativeCard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private double accumulativeSum;
	
	@OneToOne(mappedBy = "card")
	private Customer customer;

	public AccumulativeCard() {	}

	public AccumulativeCard(double accumulativeSum) {
		this.accumulativeSum = accumulativeSum;
	}

	public AccumulativeCard(Long id, double accumulativeSum) {
		super();
		this.id = id;
		this.accumulativeSum = accumulativeSum;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getAccumulativeSum() {
		return accumulativeSum;
	}

	public void setAccumulativeSum(double accumulativeSum) {
		this.accumulativeSum += accumulativeSum;
	}
}
