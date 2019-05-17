package poem.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import poem.hexagon.boundary.command.AskForPoem;
import poem.springboot.driveradapter.WebDriver;

@Controller
public class PoemController { 
	private WebDriver webDriver;

	@Autowired
	public PoemController(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
		
	@GetMapping("/askForPoem")
	public String askForPoem(@RequestParam(name = "lang", required = false, defaultValue = "en") String language, Model webModel) {
		webDriver.reactTo(new AskForPoem(language), webModel);
		return "poemView";
	}
}