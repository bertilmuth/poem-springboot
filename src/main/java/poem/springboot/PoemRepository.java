package poem.springboot;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoemRepository extends CrudRepository<Poem, Long> {
	Collection<Poem> findByLanguage(String language);
}