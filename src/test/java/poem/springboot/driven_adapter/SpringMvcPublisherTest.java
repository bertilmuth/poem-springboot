package poem.springboot.driven_adapter;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;

import poem.springboot.boundary.SpringMvcBoundary;
import poem.springboot.driver_adapter.PoemController;

public class SpringMvcPublisherTest {
	private MockMvc mockMvc;

	private static final String EXPECTED_ENGLISH_POEM = PoemObtainerStub.ENGLISH_POEM;
	private static final String EXPECTED_GERMAN_POEM = PoemObtainerStub.GERMAN_POEM;

	@Before
	public void setup() {
		PoemObtainerStub poemObtainerStub = new PoemObtainerStub();
		SpringMvcBoundary webDriver = new SpringMvcBoundary(poemObtainerStub);
		PoemController poemController = new PoemController(webDriver);
		this.mockMvc = MockMvcBuilders.standaloneSetup(poemController).build();
	}

	@Test
	public void poemIsEnglishByDefault() throws Exception {
		MvcResult result = mockMvc.perform(get("/askForPoem")).andReturn();
		assertPoemIs(EXPECTED_ENGLISH_POEM, result);
	}

	@Test
	public void englishPoem() throws Exception {
		MvcResult result = mockMvc.perform(get("/askForPoem").param("lang", "en")).andReturn();
		assertPoemIs(EXPECTED_ENGLISH_POEM, result);
	}

	@Test
	public void englishPoemWhenUnknownLanguage() throws Exception {
		MvcResult result = mockMvc.perform(get("/askForPoem").param("lang", "fr")).andReturn();
		assertPoemIs(EXPECTED_ENGLISH_POEM, result);
	}

	@Test
	public void germanPoem() throws Exception {
		MvcResult result = mockMvc.perform(get("/askForPoem").param("lang", "de")).andReturn();
		assertPoemIs(EXPECTED_GERMAN_POEM, result);
	}

	private void assertPoemIs(String expectedPoemVerses, MvcResult result) {
		ModelAndView modelAndView = result.getModelAndView();
		String[] actualPoemVerses = (String[]) modelAndView.getModelMap().get(SpringMvcPublisher.LINES_ATTRIBUTE);
		assertEquals(expectedPoemVerses, actualPoemVerses[0]);
	}
}
