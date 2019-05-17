package poem.springboot.driveradapter;

import org.springframework.ui.Model;

public interface IDriveSpringMvc {
	void reactTo(Object command, Model webModel);
}