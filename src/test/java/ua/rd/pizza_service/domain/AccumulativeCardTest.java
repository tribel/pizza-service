package ua.rd.pizza_service.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class AccumulativeCardTest {

	@Test
	public void testAddAccumulativeSum() {
		AccumulativeCard testCard = new AccumulativeCard();
		testCard.setAccumulativeSum(10.0);
		
		assertEquals(10.0, testCard.getAccumulativeSum(), 0.01);
		
		testCard.setAccumulativeSum(7.0);
		assertEquals(17.0, testCard.getAccumulativeSum(), 0.01);
	}

}
