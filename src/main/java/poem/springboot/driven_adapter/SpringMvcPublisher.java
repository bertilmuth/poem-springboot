package poem.springboot.driven_adapter;

import java.util.Objects;

import org.springframework.ui.Model;

import poem.boundary.driven_port.IWriteLines;

public class SpringMvcPublisher implements IWriteLines {
	static final String LINES_ATTRIBUTE = "lines";

	private Model webModel;
	
	public SpringMvcPublisher(Model webModel) {
		this.webModel = webModel;
	}
	
	public void writeLines(String[] lines) {
		Objects.requireNonNull(lines);
		webModel.addAttribute(LINES_ATTRIBUTE, lines);
	}
}