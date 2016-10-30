package ua.rd.pizza_service.domain.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import ua.rd.pizza_service.domain.Customer;
import ua.rd.pizza_service.domain.Pizza;
import ua.rd.pizza_service.domain.discount.DiscountType;



//@Component
//@Scope("prototype")
@Entity
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.PERSIST)  
	@JoinColumn(name = "customer_id" )
	private Customer customer;
	
	@ElementCollection
	@CollectionTable(name = "orders_pizzas", joinColumns = @JoinColumn(name = "order_id"))
	@MapKeyJoinColumn(name = "pizza_id")
	@Column(name = "pizza_count")
	private Map<Pizza, Integer> pizzaMap;
	
	private LocalDateTime date;

	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	@Transient
	private List<DiscountType> discountList;
	

	public enum OrderStatus {
		NEW, IN_PROGRSS, DONE, CANCELED;
		
		private enum Transition {
			FROM_NEW(NEW, IN_PROGRSS, CANCELED),
			FROM_IN_PROGRSS(IN_PROGRSS, CANCELED, DONE),
			FROM_CANCEL(CANCELED),
			FROM_DONE(DONE);
			
			OrderStatus from;
			EnumSet<OrderStatus> toStatuses;
			
			private Transition(OrderStatus from, OrderStatus... to) {
				this.from = from;
				toStatuses = EnumSet.noneOf(OrderStatus.class);
				toStatuses.addAll(Arrays.asList(to));
			}
			
			static final Map<OrderStatus, Set<OrderStatus>> transitions = 
					new EnumMap<>(OrderStatus.class);
			
			static {
				for(Transition t: Transition.values()) {
					transitions.put(t.from, EnumSet.copyOf(t.toStatuses));
				}
			}
		}
		
		public boolean canChangeTo(OrderStatus status) {
			return Transition.transitions.get(this).contains(status);
		}
	}

	public Order() {
		this.status = OrderStatus.NEW;
	}

	public Order(List<DiscountType> list) {
		this.discountList = list;
	}
	
	
	
	public Order(Long id, Customer customer, List<DiscountType> discountList) {
		this.id = id;
		this.customer = customer;
		this.status = OrderStatus.NEW;
		this.discountList = discountList;
	}

	public Order(Customer customer, List<Pizza> pizzaList) {
		super();
		this.customer = customer;
		setPizzaList(pizzaList);
		this.status = OrderStatus.NEW;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	protected Map<Pizza, Integer> getPizzaMap() {
		return pizzaMap;
	}
	
	protected void setPizzaMap(Map<Pizza, Integer> pizzaMap) {
		this.pizzaMap = pizzaMap;
	}

	public List<Pizza> getPizzaList() {
		List<Pizza> returnList = new ArrayList<>();
		for(Map.Entry<Pizza, Integer> entr: this.pizzaMap.entrySet()) {
			for(int i = 0; i < entr.getValue(); i++) {
				returnList.add(entr.getKey());
			}	
		}
		return returnList;
	}

	public void setPizzaList(List<Pizza> list) {
		this.pizzaMap = new HashMap<>(list.size());
		for(Pizza p: list) {
			addPizza(p);
		}
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void addPizza(Pizza pizza) {
		Integer value  = pizzaMap.get(pizza);
		pizzaMap.put(pizza, (value == null) ? 1: value + 1);
	}

	public OrderStatus getStatus() {
		return status;
	}
	
	public void setStatus(OrderStatus status) {
		if(!this.status.canChangeTo(status)) {
			throw new IllegalArgumentException("Changing status invalid");
		}
		this.status = status;
	}

	public LocalDateTime getDate() {
		return date;
	}
	
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
	public void orderCancel() {
		this.status = OrderStatus.CANCELED;
	}
	
	
	public void setDiscountList(List<DiscountType> discountList) {
		this.discountList = discountList;
	}
	
	public List<DiscountType> getDiscountList() {
		return discountList;
	}

	public void putOrderPriceToAccumulativeCard() {
		if(isAccumulativeCartExist())
			this.customer.getCard().setAccumulativeSum(this.calculateOrderPriceWithDiscount());

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
		double sumResult = 0.0;
		for(Map.Entry<Pizza, Integer> entry: pizzaMap.entrySet()) {
			sumResult += entry.getKey().getPrice() * entry.getValue();
		}
		return sumResult;
	}
	
	@Override
	public String toString() {
		return "Order [id=" + id + ", customer=" + customer + ", pizzaList=" + pizzaMap + "]";
	}
	
	

}
