package ua.rd.pizza_service.domain;

public class AccumulativeCard {

	private double accumulativeSum;

	public AccumulativeCard() {	}

	public AccumulativeCard(double accumulativeSum) {
		this.accumulativeSum = accumulativeSum;
	}

	public double getAccumulativeSum() {
		return accumulativeSum;
	}

	public void setAccumulativeSum(double accumulativeSum) {
		this.accumulativeSum += accumulativeSum;
	}
}
