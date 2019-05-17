package poem.springboot.driveradapter;

import org.springframework.ui.Model;

import poem.hexagon.boundary.driverport.IReactToCommands;
import poem.springboot.drivenadapter.WebPublisher;

public class WebDriver implements IDriveSpringMvc{
	private IReactToCommands driverPort;
	private WebPublisher webPublisher;

	public WebDriver(IReactToCommands driverPort, WebPublisher webPublisher) {
		this.driverPort = driverPort;
		this.webPublisher = webPublisher;
	}

	@Override
	public void reactTo(Object command, Model webModel) {
		webPublisher.setWebModel(webModel);
		driverPort.reactTo(command);
	}
}
