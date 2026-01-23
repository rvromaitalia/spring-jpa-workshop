package se.lexicon.jpaworkshopus.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.jpaworkshopus.entity.Author;

import java.util.List;

public interface AuthorRepo extends JpaRepository<Author, Integer> {

    // Find by first name.
    List<Author> findByFirstName(String firstName);

    // Find by last name
    List<Author> findByLastName(String lastName);

    //Find by first name or last name containing a keywor
    List<Author> findByFirstNameContainingOrLastNameContaining(String firstNameKeyword, String lastNameKeyword);

    //Find by a book's ID.
    List<Author> findByWrittenBooksId(int bookId);

    //Update name by ID
    @Modifying
    @Transactional
    @Query("UPDATE Author a SET a.firstName = :firstName, a.lastName = :lastName WHERE a.id = :id")
    int updateNameById(int id, String firstName, String lastName);

    // Delete by ID.
    @Modifying
    @Transactional
    @Query("DELETE FROM Author a WHERE a.id = :id")
    int deleteAuthorById(int id);



}
