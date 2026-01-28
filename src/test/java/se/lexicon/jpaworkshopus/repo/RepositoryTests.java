package se.lexicon.jpaworkshopus.repo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import se.lexicon.jpaworkshopus.entity.AppUser;
import se.lexicon.jpaworkshopus.entity.Book;
import se.lexicon.jpaworkshopus.entity.BookLoan;
import se.lexicon.jpaworkshopus.entity.Details;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class RepositoryTests {

    @Autowired AppUserRepo appUserRepo;
    @Autowired BookRepo bookRepo;
    @Autowired BookLoanRepo bookLoanRepo;

    private AppUser user1;
    private AppUser user2;
    private Book book1;
    private Book book2;

    private LocalDate baseDate;
    private BookLoan overdueLoan;
    private BookLoan activeLoan;

    @BeforeEach
    void setUp() {
        // Use a fixed date to avoid time-dependent/flaky tests
        baseDate = LocalDate.of(2026, 1, 15);

        Details det1 = new Details(0, "swederom@msn.com", "Artur", LocalDate.of(1978, 1, 18), null);
        Details det2 = new Details(0, "serom@msn.com", "Roman", LocalDate.of(1976, 2, 17), null);

        //user1 = appUserRepo.save(new AppUser(0, "user3", "password3", baseDate, det1));
        //user2 = appUserRepo.save(new AppUser(0, "user4", "password4", baseDate, det2));

        book1 = bookRepo.save(new Book("aaaa1111", 30, "java with Springboot"));
        book2 = bookRepo.save(new Book("abcd456", 10, "pure Java for devops"));

        // Persist loans and keep references for stronger assertions
        overdueLoan = bookLoanRepo.save(new BookLoan(
                0,
                baseDate.minusDays(2),
                baseDate.minusDays(1),
                false,
                book1,
                user2
        ));

        activeLoan = bookLoanRepo.save(new BookLoan(
                0,
                baseDate,
                baseDate.plusDays(10),
                false,
                book2,
                user2
        ));
    }

    // ---------- BookRepo tests ----------

    @Test
    void findByIsbnIgnoreCase_shouldFindBook() {
        var found = bookRepo.findByIsbnIgnoreCase("aaaa1111");
        assertThat(found.get().getIsbn()).isEqualToIgnoringCase("aaaa1111");
    }

    @Test
    void findByTitleContainingIgnoreCase_shouldReturnMatchingBooks() {
        List<Book> result = bookRepo.findByTitleContainingIgnoreCase("java");

        assertThat(result).hasSize(2);
        assertThat(result).extracting(Book::getId).containsExactlyInAnyOrder(book1.getId(), book2.getId());
    }

    @Test
    void findByMaxLoanDaysLessThan_shouldReturnOnlyShortLoanBooks() {
        List<Book> result = bookRepo.findByMaxLoanDaysLessThan(20);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getMaxLoanDays()).isLessThan(20);
    }

    // ---------- BookLoanRepo tests ----------

    @Test
    void findByBorrowerId_shouldReturnLoansForBorrower() {
        List<BookLoan> loans = bookLoanRepo.findByBorrowerId(user2.getId());
        assertThat(loans).hasSize(2);
    }

    @Test
    void findLoansByReturnedFalse_shouldReturnAllNotReturned() {
        List<BookLoan> loans = bookLoanRepo.findLoansByReturnedFalse();
        assertThat(loans).hasSize(2);
    }

    @Test
    void findByDueDateBeforeAndReturnedFalse_shouldReturnOnlyOverdue() {
        List<BookLoan> overdue = bookLoanRepo.findByDueDateBeforeAndReturnedFalse(baseDate);

        assertThat(overdue.get(0).getId()).isEqualTo(overdueLoan.getId());
        assertThat(overdue.get(0).getDueDate()).isBefore(baseDate);
        assertThat(overdue.get(0).isReturned()).isFalse();
    }
}