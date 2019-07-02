package poem.springboot.driver_adapter;

import poem.boundary.driver_port.IReactToCommands;

public class SpringMvcDriver{
	private IReactToCommands driverPort;

	public SpringMvcDriver(IReactToCommands driverPort) {
		this.driverPort = driverPort;
	}

	public void reactTo(Object command) {
		driverPort.reactTo(command);
	}
}
