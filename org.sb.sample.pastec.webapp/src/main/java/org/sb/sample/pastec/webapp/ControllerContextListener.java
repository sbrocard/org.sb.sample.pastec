package org.sb.sample.pastec.webapp;
import javax.inject.Inject;
import javax.servlet.ServletContextEvent;  
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ControllerContextListener implements ServletContextListener {

	@Inject
	private Controller controller;

	public ControllerContextListener() {
	}
	
	public ControllerContextListener(Controller controller) {
		this.controller = controller;
	}
	
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("Starting up!");
//        controller.init();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("Shutting down!");
    }
}