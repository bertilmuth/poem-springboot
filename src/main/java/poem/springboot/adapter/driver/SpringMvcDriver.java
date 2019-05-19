package poem.springboot.adapter.driver;

import org.springframework.ui.Model;

import poem.hexagon.boundary.driverport.IReactToCommands;
import poem.springboot.adapter.driven.SpringMvcPublisher;

public class SpringMvcDriver{
	private IReactToCommands driverPort;
	private SpringMvcPublisher webPublisher;

	public SpringMvcDriver(IReactToCommands driverPort, SpringMvcPublisher webPublisher) {
		this.driverPort = driverPort;
		this.webPublisher = webPublisher;
	}

	public void reactTo(Object command, Model webModel) {
		webPublisher.setWebModel(webModel);
		driverPort.reactTo(command);
	}
}