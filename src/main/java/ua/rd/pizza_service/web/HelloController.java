package ua.rd.pizza_service.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ua.rd.pizza_service.services.PizzaService;
import ua.rd.pizza_service.web.ifrastructure.MyController;

@Controller("/hello")
public class HelloController implements MyController{

	@Autowired
	private PizzaService pizzaService;
	
	@Override
	public void handleRequest(HttpServletRequest req, HttpServletResponse resp) {

		try (PrintWriter out = resp.getWriter()) {
            out.println(pizzaService.findAll());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}
