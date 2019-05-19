package poem.springboot;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import poem.hexagon.boundary.Boundary;
import poem.springboot.adapter.driven.PoemObtainerStub;
import poem.springboot.adapter.driven.WebPublisher;
import poem.springboot.adapter.driver.WebDriver;

public class PoemControllerTest {
    private MockMvc mockMvc;
	private WebPublisher webPublisher;
    
    private static final String EXPECTED_ENGLISH_POEM = PoemObtainerStub.ENGLISH_POEM;
    private static final String EXPECTED_GERMAN_POEM = PoemObtainerStub.GERMAN_POEM;
    
    @Before
    public void setup() {
    	PoemObtainerStub poemObtainerStub = new PoemObtainerStub();
    	webPublisher = new WebPublisher();
    	Boundary boundary = new Boundary(poemObtainerStub, webPublisher);
    	WebDriver webDriver = new WebDriver(boundary, webPublisher);
    	PoemController poemController = new PoemController(webDriver);
    	this.mockMvc = MockMvcBuilders.standaloneSetup(poemController).build();
    }

    @Test
    public void poemIsEnglishByDefault() throws Exception {
        mockMvc.perform(get("/askForPoem"));
        assertPoemIs(EXPECTED_ENGLISH_POEM);
    }

    @Test
    public void englishPoem() throws Exception {
        mockMvc.perform(get("/askForPoem").param("lang", "en"));
        assertPoemIs(EXPECTED_ENGLISH_POEM);
    }
    
    @Test
    public void englishPoemWhenUnknownLanguage() throws Exception {
        mockMvc.perform(get("/askForPoem").param("lang", "fr"));
        assertPoemIs(EXPECTED_ENGLISH_POEM);
    }

    @Test
    public void germanPoem() throws Exception {
        mockMvc.perform(get("/askForPoem").param("lang", "de"));
        assertPoemIs(EXPECTED_GERMAN_POEM);
    }
    
	private void assertPoemIs(String expectedPoemVerse) {
		String[] actualPoemVerses = webPublisher.getLines();
        assertEquals(expectedPoemVerse, actualPoemVerses[0]);
	}
}
