package se.lexicon.jpaworkshopus.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.jpaworkshopus.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepo extends JpaRepository <Book, Integer>{
    Optional<Book> findByIsbnIgnoreCase(String isbn);

    List<Book> findByTitleContainingIgnoreCase(String titlePart);

    List<Book> findByMaxLoanDaysLessThan(int maxLoanDays);
}
