package ua.rd.pizza_service.infrastructure;


public interface Config {

	Class<?> getImpl(String name);
	
}
