package ua.rd.pizza_service.web.ifrastructure;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ua.rd.pizza_service.web.HelloController;

/**
 * Servlet implementation class DispatcherServlet
 */
public class DispatcherServlet extends HttpServlet {

	private ConfigurableApplicationContext webContext;
	ConfigurableApplicationContext[] applicationContexts;

	@Override
	public void init() {
		String contextsLocations = getServletContext().getInitParameter("contextConfigLocation");
		String[] contexts = contextsLocations.split(" ");

		applicationContexts = new ConfigurableApplicationContext[contexts.length];

		for (int i = 0; i < applicationContexts.length; i++) {
			ConfigurableApplicationContext context;
			if (i == 0) {
				context = new ClassPathXmlApplicationContext(contexts[i]);
			} else {
				context = new ClassPathXmlApplicationContext(new String[] { contexts[i] }, applicationContexts[i - 1]);
			}
			applicationContexts[i] = context;
		}

		String webContextConfigLocation = getInitParameter("contextConfigLocation");
		webContext = new ClassPathXmlApplicationContext(new String[] { webContextConfigLocation },
				applicationContexts[applicationContexts.length - 1]);
	}

	@Override
	public void destroy() {
		webContext.close();
		for (int i = applicationContexts.length - 1; i >= 0; i--) {
			applicationContexts[i].close();
		}
	}

	protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*String url = req.getRequestURI();
		String controllerName = getCotrollerName(url);*/

		HandlerMapping handlerMapping = webContext.getBean("handlerMappingSrategy", HandlerMapping.class); 
				//new SimpleURLHandlerMapping(webContext);
		
		MyController controller = handlerMapping.getController(req);//(MyController) webContext.getBean(controllerName);// getController(controllerName);
		if (controller != null) {
			controller.handleRequest(req, resp);
		}

	}

	private MyController getController(String controllerName) {
		return null;
	}

	private String getCotrollerName(String url) {
		return url.substring(url.lastIndexOf("/"));
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}
}
