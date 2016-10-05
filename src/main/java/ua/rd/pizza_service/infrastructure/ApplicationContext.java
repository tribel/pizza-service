package ua.rd.pizza_service.infrastructure;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class ApplicationContext implements Context {

	private final Config config;
	private Map<String, Object> beans = new HashMap<>();
	
	public ApplicationContext(Config config) {
		this.config = config;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getBean(String beanName) {
		
		if(beans.containsKey(beanName)) {
			return (T)beans.get(beanName);
		}
		
		Class<?> type = config.getImpl(beanName);
		Constructor<?> constructor = type.getConstructors()[0];
		
		if(constructor.getParameterCount() == 0) {
			return addNewBean(type, beanName);
				
		} else {
			
			T bean;
			try {
				 bean = (T) constructor.newInstance(createDependencyBeans(constructor));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			
			beans.put(beanName, bean);
			return bean;
		}
		
	}

	private String convertTypeToBeanName(Class<?> paramClass) {
		StringBuilder builder = new StringBuilder(paramClass.getSimpleName());
		builder.setCharAt(0, Character.toLowerCase(builder.charAt(0)));
		return builder.toString();
	}
	
	@SuppressWarnings("unchecked")
	private <T> T addNewBean(Class<?> type, String beanName) {
		try {
			T tmpBean = (T) type.newInstance();
			beans.put(beanName, tmpBean);
			return  tmpBean;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private Object[] createDependencyBeans(Constructor<?> constructor) {
		Class<?>[] parameterTypes = constructor.getParameterTypes();
		Object[] params = new Object[constructor.getParameterCount()];
		
		for(int i = 0; i < constructor.getParameterCount(); i++) {
			String tmpBeanName = convertTypeToBeanName(parameterTypes[i]);
			params[i] = getBean(tmpBeanName);
		}
	
		return params;
	}	


}
