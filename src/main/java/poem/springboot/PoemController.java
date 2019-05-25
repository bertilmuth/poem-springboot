package poem.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import poem.command.AskForPoem;
import poem.springboot.driver_adapter.SpringMvcDriver;

@Controller
class PoemController { 
	private SpringMvcDriver springMvcDriver;

	@Autowired
	public PoemController(SpringMvcDriver springMvcDriver) {
		this.springMvcDriver = springMvcDriver;
	}
		
	@GetMapping("/askForPoem")
	public String askForPoem(@RequestParam(name = "lang", required = false, defaultValue = "en") String language, Model webModel) {
		// Forward commands to the hexagon, by using SpringMvcDriver
		springMvcDriver.reactTo(new AskForPoem(language), webModel);
		return "poemView";
	}
}
