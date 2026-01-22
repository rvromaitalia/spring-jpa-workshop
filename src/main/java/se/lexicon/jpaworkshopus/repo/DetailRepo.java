package se.lexicon.jpaworkshopus.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.jpaworkshopus.entity.Details;

import java.util.List;
import java.util.Optional;

public interface DetailRepo extends JpaRepository<Details, Integer> {
    Optional<Details> findByEmail(String email);
    List<Details> findByNameContaining(String namePart);
    List<Details> findByNameContainingIgnoreCase(String namePart);
    List<Details> findByNameIgnoreCase(String name);
}
