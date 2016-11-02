package ua.rd.pizza_service.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ua.rd.pizza_service.domain.Pizza;
import ua.rd.pizza_service.services.PizzaService;

@RestController
public class PizzaRestController {

	@Autowired
	private PizzaService pizzaSerivce;
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String[] hello() {
		return new String[]{"<b>hello from rest controller</b>"};
	}
	
	@RequestMapping(value = "pizza/{pizzaId}", method = RequestMethod.GET)
	public Pizza pizza(@PathVariable("pizzaId") Integer id) {
		return pizzaSerivce.find(id); 
	}

	
	@RequestMapping(value = "pizza",
			method = RequestMethod.POST,
			consumes = "application/json")
	public void pizza(@RequestBody Pizza pizza) {
		System.out.println(pizza);
	}

}
