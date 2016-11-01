package ua.rd.pizza_service.web.ifrastructure;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MyController {

	void handleRequest(HttpServletRequest req, HttpServletResponse resp);

	
}
