package se.lexicon.jpaworkshopus;

import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.lexicon.jpaworkshopus.entity.AppUser;
import se.lexicon.jpaworkshopus.entity.Book;
import se.lexicon.jpaworkshopus.entity.BookLoan;
import se.lexicon.jpaworkshopus.entity.Details;
import se.lexicon.jpaworkshopus.repo.AppUserRepo;
import se.lexicon.jpaworkshopus.repo.BookLoanRepo;
import se.lexicon.jpaworkshopus.repo.BookRepo;
import se.lexicon.jpaworkshopus.repo.DetailRepo;

import java.time.LocalDate;
import java.util.List;

@Component
public class AppCommandLineRunner implements CommandLineRunner {

    private final AppUserRepo appUserRepo;
    private final DetailRepo detailRepo;
    private final BookLoanRepo bookLoanrepo;
    private final BookRepo bookRepo;

    @Autowired
    public AppCommandLineRunner(
            AppUserRepo appUserRepo,
            DetailRepo detailRepo,
            BookLoanRepo bookLoanrepo,
            BookRepo bookRepo) {
        this.appUserRepo = appUserRepo;
        this.detailRepo = detailRepo;
        this.bookLoanrepo = bookLoanrepo;
        this.bookRepo = bookRepo;
    }

    @Override
    public void run(String @NonNull ... args) {

        //Details for the users
        Details det1 = detailRepo.save(new Details(0,"swederom@msn.com","Artur",LocalDate.of(1978, 1, 18),null));
        Details det2 = detailRepo.save(new Details(0,"serom@msn.com","Roman",LocalDate.of(1976, 2, 17),null));

        //Create some users
        AppUser user1 = appUserRepo.save(new AppUser(0, "user3", "password3", LocalDate.now(), detailRepo.findById(1).get()));
        AppUser user2 =appUserRepo.save(new AppUser(0, "user4", "password4", LocalDate.now(), detailRepo.findById(2).get()));

        //Create some books
        Book book1 = bookRepo.save(new Book(0, "abcd1234","java with Springboot",30, "Simon"));
        Book book2 = bookRepo.save(new Book("abcd456", 10,"pure Java for devops"));


        //Test BookRepo methods
        System.out.println("Finding a book with isbn 'abcd1234'" + bookRepo.findByIsbnIgnoreCase("abcd1234"));
        bookRepo.findByMaxLoanDaysLessThan(20).forEach(System.out::println);

        //Test BookLoanRepo methods
       bookLoanrepo.save(new BookLoan(0,LocalDate.now().minusDays(2), LocalDate.now().minusDays(1), false, book1,appUserRepo.findById(1).get()));       bookLoanrepo.save(new BookLoan(0, LocalDate.now(), LocalDate.now().plusDays(10), false, book2, appUserRepo.findById(2).get()));

       bookLoanrepo.findByBorrowerId(1).forEach(System.out::println);

       System.out.println("------ testing 'findLoansByReturnedFalse()'-----------");
       bookLoanrepo.findLoansByReturnedFalse().forEach(System.out::println);

       System.out.println("------ testing 'findByDueDateBeforeAndReturnedFalse()'-----------");
       bookLoanrepo.findByDueDateBeforeAndReturnedFalse(LocalDate.now()).forEach(System.out::println);
    }
}
