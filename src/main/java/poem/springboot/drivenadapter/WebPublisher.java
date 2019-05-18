package poem.springboot.drivenadapter;

import java.util.Map;
import java.util.Objects;

import org.springframework.ui.Model;

import poem.hexagon.boundary.drivenport.IWriteLines;

public class WebPublisher implements IWriteLines {
	public static final String lINES_ATTRIBUTE = "lines";

	private Model webModel;

	public void setWebModel(Model webModel) {
		Objects.requireNonNull(webModel);
		this.webModel = webModel;
	}

	public void writeLines(String[] poemVerses) {
		Objects.requireNonNull(poemVerses);
		webModel.addAttribute(lINES_ATTRIBUTE, poemVerses);
	}

	/**
	 * Returns the lines of the last call to {@link #writeLines(String[])}
	 * 
	 * @return the latest lines, or null if {@link #writeLines(String[])} hasn't
	 *         been called.
	 */
	public String[] getLines() {
		String[] lines = null;

		if (webModel != null) {
			Map<String, Object> attributes = webModel.asMap();
			lines = (String[]) attributes.get(lINES_ATTRIBUTE);
		}
		return lines;
	}
}