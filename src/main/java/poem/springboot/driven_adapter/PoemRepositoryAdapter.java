package poem.springboot.driven_adapter;

import java.util.Collection;
import java.util.stream.Collectors;

import poem.hexagon.boundary.drivenport.IObtainPoems;
import poem.springboot.Poem;
import poem.springboot.PoemRepository;

public class PoemRepositoryAdapter implements IObtainPoems {
	private PoemRepository poemRepository;

	public PoemRepositoryAdapter(PoemRepository poemRepository) {
		this.poemRepository = poemRepository;
	}
	
	@Override
	public String[] getMePoems(String language) {
		Collection<Poem> poems = poemRepository.findByLanguage(language);
		final String[] poemsArray = poems.stream()
			.map(p -> p.getVerses())
			.collect(Collectors.toList())
			.toArray(new String[0]);
		return poemsArray;
	}
}
