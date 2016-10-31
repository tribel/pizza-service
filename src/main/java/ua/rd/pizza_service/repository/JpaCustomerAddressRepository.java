package ua.rd.pizza_service.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import ua.rd.pizza_service.domain.CustomerAddress;

@Repository("customerAddressRepository")
public class JpaCustomerAddressRepository implements CustomerAddressRepository{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public CustomerAddress find(Long id) {
		return em.find(CustomerAddress.class, id);
	}

	@Override
	public List<CustomerAddress> findAll() {
		return em.createQuery("SELECT ca FROM CustomerAddress ca ", CustomerAddress.class)
				  .getResultList();
	}

	@Override
	public CustomerAddress findAddressByCustomerId(Long id) {
		return em.createQuery("SELECT ca FROM Customer c , CustomerAddress ca WHERE ca.customer.id = :id"
				,CustomerAddress.class)
				.setParameter("id", id)
				.getSingleResult();
	}

}
