package ua.rd.pizza_service.services;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ua.rd.pizza_service.domain.Customer;
import ua.rd.pizza_service.domain.Pizza;
import ua.rd.pizza_service.domain.discount.DiscountType;
import ua.rd.pizza_service.domain.order.Order;
import ua.rd.pizza_service.domain.order.Order.OrderStatus;
import ua.rd.pizza_service.infrastructure.Benchmark;
import ua.rd.pizza_service.repository.OrderRepository;

//@Service
public class SimpleOrderService implements OrderService{

	private PizzaService pzService;
	private OrderRepository odRepository;
	
	@Autowired
	public SimpleOrderService(PizzaService pzService, OrderRepository odRepository) {
		this.pzService = pzService;
		this.odRepository = odRepository;
	}
	

	@Benchmark(enabled = true)
	@Override
	@Transactional
	public Order placeNewOrder(Customer customer, Integer... pizzasID) {
		
		List<Pizza> tmpPizzas = new ArrayList<>();
		
		for(Integer id : pizzasID){
            tmpPizzas.add(getPizzaById(id));  
        }
		
		 Order newOrder = createNewOrder(); 
		 newOrder.setCustomer(customer);
		 newOrder.setPizzaList(tmpPizzas);
		 
		 saveOrder(newOrder);
		return newOrder;
	}
	
	protected Order createNewOrder() {
		throw new IllegalStateException("Container can`t find");
	}

	private Pizza getPizzaById(int id) {
		return pzService.find(id);
	}
	
	@Transactional
	private void saveOrder(Order order) {
		odRepository.saveOrder(order);
	}

	@Override
	public double calculateOrderSum(Order order) {
		return  order.calculateOrderPriceWithDiscount();
	}
	
	@Override
	public double calculatePureOrderSum(Order order) {
		return order.pureOrderSum();
	}


	@Override
	@Transactional
	public void putOrderPriceToAccumulativeCard(Order order) {
		order.putOrderPriceToAccumulativeCard();
	}


	@Override
	public List<DiscountType> getOrderDiscountList(Order order) {
		return order.getDiscountList();
	}


	@Override
	public List<Order> findAll() {
		return odRepository.findAll();
	}

	@Override
	public List<Order> findByDate(LocalDate minDate, LocalDate maxDate) {
		return odRepository.findByDate(minDate, maxDate);
	}

	@Override
	public Order find(Long id) {
		return odRepository.find(id);
	}


	@Override
	public List<Order> findByStatus(OrderStatus status) {
		return odRepository.findByStatus(status);
	}

	@Override
	public List<Order> findOrdersByCustomer(Long id) {
		return odRepository.findOrdersByCustomer(id);
	}

	@Override
	@Transactional
	public void markAsInProgss(Long id) {
		odRepository.markAsInProgss(id);
	}

	@Override
	@Transactional
	public void markADone(Long id) {
		odRepository.markADone(id);
	}

	@Override
	@Transactional
	public void markAsCanceled(Long id) {
		odRepository.markAsCanceled(id);
	}

	
	
}
