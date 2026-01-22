package se.lexicon.jpaworkshopus.repo;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.lexicon.jpaworkshopus.entity.BookLoan;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookLoanRepo extends JpaRepository<BookLoan, Integer> {
    // Find by borrower's ID
    List<BookLoan> findByBorrowerId(int borrowerId);

    // Find by book ID
    List<BookLoan> findByBookId(int bookId);

    //Find all book loans that have not been returned yet.
    List<BookLoan> findLoansByReturnedFalse();

    // Find all overdue book loans
    List<BookLoan> findByDueDateBeforeAndReturnedFalse(LocalDate today);

    //Find loans between dates.
    List<BookLoan> findByLoanDateBetween(LocalDate start, LocalDate end);

    @Modifying
    @Transactional
    @Query("update BookLoan bl set bl.returned = true where bl.id = :loanId")
    //Correct
    int markAsReturned(@Param("loanId") int loanId);
}
