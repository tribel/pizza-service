package ua.rd.pizza_service.infrastructure;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class BenchmarkBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessAfterInitialization(Object bean, String name) throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String name) throws BeansException {
		Method[] methods = bean.getClass().getMethods();
		for (Method m : methods) {
			Benchmark benchmark = m.getAnnotation(Benchmark.class);
			if (m.isAnnotationPresent(Benchmark.class) && benchmark.enabled()) {
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
