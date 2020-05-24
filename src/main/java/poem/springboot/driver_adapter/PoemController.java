package poem.springboot.driver_adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import poem.command.AskForPoem;
import poem.springboot.boundary.SpringMvcBoundary;

@Controller
public class PoemController {
	private SpringMvcBoundary springMvcBoundary;

	@Autowired
	public PoemController(SpringMvcBoundary springMvcBoundary) {
		this.springMvcBoundary = springMvcBoundary;
	}

	@GetMapping("/askForPoem")
	public String askForPoem(@RequestParam(name = "lang", required = false, defaultValue = "en") String language,
			Model webModel) {
		springMvcBoundary.basedOn(webModel).reactTo(new AskForPoem(language));

		return "poemView";
	}
}
