package ua.rd.pizza_service.web.ifrastructure;

import javax.servlet.http.HttpServletRequest;

public interface HandlerMapping {
	
	MyController getController(HttpServletRequest request);
}
