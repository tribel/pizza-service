package ua.rd.pizza_service.web.ifrastructure;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SimpleURLHandlerMapping implements HandlerMapping, ApplicationContextAware{

	private ApplicationContext web;
	
	
	
/*	public SimpleURLHandlerMapping(ApplicationContext web) {
		this.web = web;
	}*/

	@Override
	public MyController getController(HttpServletRequest request) {
		String url = request.getRequestURI();
		String controlName = getControllerName(url);
		return web.getBean(controlName, MyController.class);
		
	}
	
	private String getControllerName(String url) {
		return url.substring(url.lastIndexOf("/"));
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.web = applicationContext;
	}

}
