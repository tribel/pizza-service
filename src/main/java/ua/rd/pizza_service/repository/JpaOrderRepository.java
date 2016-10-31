package ua.rd.pizza_service.repository;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import ua.rd.pizza_service.domain.order.Order;
import ua.rd.pizza_service.domain.order.Order.OrderStatus;

@Repository("orderRepository")
public class JpaOrderRepository implements OrderRepository{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void saveOrder(Order order) {
		em.persist(order);
	}

	@Override
	public List<Order> findAll() {
		return em.createQuery("SELECT o FROM Orders o", Order.class).getResultList();
	}

	@Override
	public List<Order> findByDate(LocalDate minDate, LocalDate maxDate) {
		return em.createQuery("SELECT o FROM Orders o WHERE o.date BETWEEN :min AND :max", Order.class)
						.setParameter("min", minDate)
						.setParameter("max", maxDate)
						.getResultList();
	}

	@Override
	public Order find(Long id) {
		return em.find(Order.class, id);
	}

	@Override
	public List<Order> findByStatus(OrderStatus status) {
		return em.createQuery("SELECT o FROM Orders o WHERE o.status = :sts", Order.class)
						.setParameter("sts", status)
						.getResultList();
	}

	@Override
	public List<Order> findOrdersByCustomer(Long id) {
		return em.createNamedQuery("SELECT o FROM Orders o WHERE o.customer.id = :id", Order.class)
				.setParameter("id", id)
				.getResultList();
	}

	@Override
	public void markAsInProgss(Long id) {
		em.find(Order.class, id).setStatus(OrderStatus.IN_PROGRSS);
	}

	@Override
	public void markADone(Long id) {
		em.find(Order.class, id).setStatus(OrderStatus.DONE);
	}

	@Override
	public void markAsCanceled(Long id) {
		em.find(Order.class, id).setStatus(OrderStatus.CANCELED);
	}

}
