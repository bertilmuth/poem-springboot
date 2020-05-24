package poem.driven_adapter;

import java.util.Collection;
import java.util.stream.Collectors;

import poem.application.Poem;
import poem.application.PoemRepository;
import poem.boundary.driven_port.IObtainPoems;

public class PoemRepositoryAdapter implements IObtainPoems {
	private PoemRepository poemRepository;

	public PoemRepositoryAdapter(PoemRepository poemRepository) {
		this.poemRepository = poemRepository;
	}
	
	@Override
	public String[] getMePoems(String language) {
		Collection<Poem> poems = poemRepository.findByLanguage(language);
		final String[] poemsArray = poems.stream()
			.map(p -> p.getText())
			.collect(Collectors.toList())
			.toArray(new String[0]);
		return poemsArray;
	}
}
