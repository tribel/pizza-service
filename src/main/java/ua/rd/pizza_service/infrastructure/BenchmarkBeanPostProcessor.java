package ua.rd.pizza_service.infrastructure;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class BenchmarkBeanPostProcessor implements BeanPostProcessor {

	private Map<String, Class<?>> beanMap = new HashMap<>();
	
	@Override
	public Object postProcessAfterInitialization(Object bean, String name) throws BeansException {
		Class<?> beanClass = beanMap.get(name);
		if(beanClass != null) {
			Object targenBean = bean;
			bean = Proxy.newProxyInstance(bean.getClass().getClassLoader(), getAllDeclaredInterfaces(bean),
					new InvocationHandler() {
	
						@Override
						public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
							long startTime = System.nanoTime();
							Object object = method.invoke(targenBean, args);
							long endTime = System.nanoTime();
	
							System.out.println("Total execution time= " + (endTime - startTime));
							return object;
						}
					});
			}
			return bean;
	}

	@Override
	
	public Object postProcessBeforeInitialization(Object bean, String name) throws BeansException {
		Method[] methods = bean.getClass().getMethods();
		for(Method m: methods) {
			Benchmark benchmark = m.getAnnotation(Benchmark.class);
			if (m.isAnnotationPresent(Benchmark.class) && benchmark.enabled()) {
				beanMap.put(name, bean.getClass());
			}
		}
		return bean;
	}

	private Class<?>[] getAllDeclaredInterfaces(Object o) {
		List<Class<?>> interfaces = new ArrayList<>();
		Class<?> klazz = o.getClass();
		while (klazz != null) {
			Collections.addAll(interfaces, klazz.getInterfaces());
			klazz = klazz.getSuperclass();
		}
		return interfaces.stream().toArray(Class<?>[]::new);
	}


}
