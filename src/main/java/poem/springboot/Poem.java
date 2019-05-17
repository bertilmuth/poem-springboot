package poem.springboot;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Poem {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotBlank(message = "Language is mandatory")
	private String language;

	@NotBlank(message = "Verses are mandatory")
	@Column(length = 10000)
	private String verses;

	public long getId() {
		return id;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getVerses() {
		return verses;
	}

	public void setVerses(String verses) {
		this.verses = verses;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((language == null) ? 0 : language.hashCode());
		result = prime * result + ((verses == null) ? 0 : verses.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Poem other = (Poem) obj;
		if (id != other.id)
			return false;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
			return false;
		if (verses == null) {
			if (other.verses != null)
				return false;
		} else if (!verses.equals(other.verses))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Poem [id=" + id + ", language=" + language + ", verses=" + verses + "]";
	}
}