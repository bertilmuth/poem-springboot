/*
 * Copyright 2012-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package poem.springboot;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import poem.hexagon.boundary.Boundary;
import poem.springboot.drivenadapter.PoemObtainerStub;
import poem.springboot.drivenadapter.WebPublisher;
import poem.springboot.driveradapter.WebDriver;

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
    public void germanPoem() throws Exception {
        mockMvc.perform(get("/askForPoem").param("lang", "de"));
        assertPoemIs(EXPECTED_GERMAN_POEM);
    }
    
	private void assertPoemIs(String expectedPoemVerse) {
		String[] actualPoemVerses = webPublisher.getPoemVerses();
        assertEquals(expectedPoemVerse, actualPoemVerses[0]);
	}
}
