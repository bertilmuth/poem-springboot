package poem.springboot.drivenadapter;

import java.util.Map;
import java.util.Objects;

import org.springframework.ui.Model;

import poem.hexagon.boundary.drivenport.IWriteLines;

public class WebPublisher implements IWriteLines {
	public static final String POEM_VERSES = "poemVerses";
	
	private Model webModel;
	
	public void setWebModel(Model webModel) {
		this.webModel = webModel;
	}
	
	public void writeLines(String[] poemVerses) { 
		Objects.requireNonNull(poemVerses);
		webModel.addAttribute(POEM_VERSES, poemVerses);
	}
	
	/**
	 * Returns the verses of the last call to {@link #writeLines(String[])}
	 * 
	 * @return the latest verses, or null if {@link #writeLines(String[])} hasn't been called.
	 */
	public String[] getPoemVerses() {
		String[] poemVerses = null;
		
		if(webModel != null) {
			Map<String, Object> attributes = webModel.asMap();
	        poemVerses = (String[])attributes.get(POEM_VERSES);
		}
		return poemVerses;
	}
}