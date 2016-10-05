package ua.rd.pizza_service.infrastructure;

public interface Context {

	
	<T> T getBean(String beanName);
}
