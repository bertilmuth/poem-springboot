package poem.springboot.boundary;

import org.springframework.ui.Model;

import poem.boundary.Boundary;
import poem.boundary.driven_port.IObtainPoems;
import poem.boundary.driver_port.IReactToCommands;
import poem.springboot.driven_adapter.SpringMvcPublisher;

public class SpringMvcBoundary {
	private final IObtainPoems poemObtainer;

	public SpringMvcBoundary(IObtainPoems poemObtainer) {
		this.poemObtainer = poemObtainer;
	}

	public IReactToCommands basedOn(Model webModel) {
		SpringMvcPublisher webPublisher = new SpringMvcPublisher(webModel);
		IReactToCommands boundary = new Boundary(poemObtainer, webPublisher);
		return boundary;
	}
}
