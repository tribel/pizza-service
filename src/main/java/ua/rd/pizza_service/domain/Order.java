package ua.rd.pizza_service.domain;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ua.rd.pizza_service.domain.discount.DiscountType;



//@Component
//@Scope("prototype")
public class Order {

	private long id;
	private Customer customer;
	private List<Pizza> pizzaList;
	private Status status;
	private List<DiscountType> discountList;
	

	public enum Status {
		NEW, IN_PROGRSS, DONE, CANCELED;
		
		private enum Transition {
			FROM_NEW(NEW, IN_PROGRSS, CANCELED),
			FROM_IN_PROGRSS(IN_PROGRSS, CANCELED, DONE),
			FROM_CANCEL(CANCELED),
			FROM_DONE(DONE);
			
			Status from;
			EnumSet<Status> toStatuses;
			
			private Transition(Status from, Status... to) {
				this.from = from;
				toStatuses = EnumSet.noneOf(Status.class);
				toStatuses.addAll(Arrays.asList(to));
			}
			
			static final Map<Status, Set<Status>> transitions = 
					new EnumMap<>(Status.class);
			
			static {
				for(Transition t: Transition.values()) {
					transitions.put(t.from, EnumSet.copyOf(t.toStatuses));
				}
			}
		}
		
		public boolean canChangeTo(Status status) {
			return Transition.transitions.get(this).contains(status);
		}
	}

	public Order() {
	}

	public Order(List<DiscountType> list) {
		this.discountList = list;
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
		if(!this.status.canChangeTo(status)) {
			throw new IllegalArgumentException("Changing status invalid");
		}
		this.status = status;
	}

	public void orderCancel() {
		this.status = Status.CANCELED;
	}
	
	
	public void setDiscountList(List<DiscountType> discountList) {
		this.discountList = discountList;
	}
	
	public void putOrderPriceToAccumulativeCard() {
		if(isAccumulativeCartExist())
			this.customer.getCard().setAccumulativeSum(this.calculateOrderPriceWithDiscount());
		customer.getCard().getAccumulativeSum();
	}
	

	public double calculateOrderPriceWithDiscount() {
		double pureOrderSum = pureOrderSum();
		
		for(DiscountType d: discountList) {
			if(d.isАpplicable(this)) {
				pureOrderSum -= d.doDiscount(this);
			}
		}
		
		return pureOrderSum;
	}
	
	private boolean isAccumulativeCartExist() {
		return (customer.getCard() != null) ? true : false;
	}

	
	public double pureOrderSum() {
		return pizzaList.stream().mapToDouble(Pizza::getPrice).sum();
	}
	
	@Override
	public String toString() {
		return "Order [id=" + id + ", customer=" + customer + ", pizzaList=" + pizzaList + "]";
	}
	
	

}
