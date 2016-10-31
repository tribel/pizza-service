package ua.rd.pizza_service.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ua.rd.pizza_service.domain.Pizza;
import ua.rd.pizza_service.domain.Pizza.PizzaType;
import ua.rd.pizza_service.repository.PizzaRepository;

@RunWith(MockitoJUnitRunner.class)
public class SimplePizzaServiceTest {

	@Mock
	private PizzaRepository repository;
	private PizzaService service ;
	
	@Before
	public void initialize() {
		service = new SimplePizzaService(repository);
	}

	@Test
	public void testSave() {
		Pizza pizza = mock(Pizza.class);
		when(repository.save(pizza)).thenReturn(pizza);
		
		service.save(pizza);
		
		verify(repository, times(1)).save(pizza);
	}

	@Test
	public void testFind() {
		service.find(1);
		verify(repository, times(1)).find(1);
	}

	@Test
	public void testUpdate() {
		Pizza pizza = mock(Pizza.class);
		service.update(pizza);
		verify(repository, times(1)).update(pizza);
	}

	@Test
	public void testDeactivatePizza() {
		service.deactivatePizza(1);
		verify(repository, times(1)).deactivatePizza(1);
	}

	@Test
	public void testActivatePizza() {
		service.activatePizza(1);
		verify(repository, times(1)).activatePizza(1);
	}

	@Test
	public void testFindAll() {
		when(repository.findAll()).thenReturn(new ArrayList<>());
		List<Pizza> list = service.findAll();
		
		assertNotNull(list);
		verify(repository, times(1)).findAll();
	}

	@Test
	public void testFindActivePizzaList() {
		List<Pizza> list = spy(new ArrayList<>());
		when(list.size()).thenReturn(10);
		when(repository.findActivePizzaList()).thenReturn(list);
		
		List<Pizza> pizzas = service.findActivePizzaList();
		assertNotNull(pizzas);
		assertEquals(10, pizzas.size());
		verify(repository, times(1)).findActivePizzaList();
	}

	@Test
	public void testFindDeactivePizzaList() {
		List<Pizza> list = spy(new ArrayList<>());
		when(list.size()).thenReturn(15);
		when(repository.findDeactivePizzaList()).thenReturn(list);
		
		List<Pizza> pizzas = service.findDeactivePizzaList();
		assertNotNull(pizzas);
		assertEquals(15, pizzas.size());
		verify(repository, times(1)).findDeactivePizzaList();
	}

	@Test
	public void testFindByCatogory() {
		List<Pizza> list = spy(new ArrayList<>());
		when(list.size()).thenReturn(5);
		when(repository.findByCatogory(PizzaType.MEAT)).thenReturn(list);
		
		List<Pizza> pizzas = service.findByCatogory(PizzaType.MEAT);
		assertNotNull(pizzas);
		assertEquals(5, pizzas.size());
		verify(repository, times(1)).findByCatogory(PizzaType.MEAT);
	}

	@Test
	public void testFindByPrice() {
		List<Pizza> list = spy(new ArrayList<>());
		when(list.size()).thenReturn(7);
		when(repository.findByPrice(10.0, 20.0)).thenReturn(list);

		List<Pizza> pizzas = service.findByPrice(10.0, 20.0);
		assertNotNull(pizzas);
		assertEquals(7, pizzas.size());
		verify(repository, times(1)).findByPrice(10.0, 20.0);
	}

}
