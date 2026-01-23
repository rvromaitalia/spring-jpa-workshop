package se.lexicon.jpaworkshopus.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.jpaworkshopus.entity.Author;

public interface AuthorRepo extends JpaRepository<Author, Integer> {

}
