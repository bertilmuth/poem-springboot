package poem.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import poem.command.AskForPoem;
import poem.springboot.driver_adapter.SpringMvcDriver;

@Controller
public class PoemController { 
	private SpringMvcDriver webDriver;

	@Autowired
	public PoemController(SpringMvcDriver webDriver) {
		this.webDriver = webDriver;
	}
		
	@GetMapping("/askForPoem")
	public String askForPoem(@RequestParam(name = "lang", required = false, defaultValue = "en") String language, Model webModel) {
		webDriver.reactTo(new AskForPoem(language), webModel);
		return "poemView";
	}
}
