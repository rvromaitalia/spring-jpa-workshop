package se.lexicon.jpaworkshopus.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@Entity
public class BookLoan {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Include
    private int id;

    @Setter @Column(nullable = false)
    @ToString.Include
    private LocalDate loanDate;

    @Setter @Column(nullable = false)
    @ToString.Include
    private LocalDate dueDate;

    @Setter@Column(nullable = false)
    private boolean returned;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "borrower_id", nullable = false)
    private AppUser borrower;

    public void calculateDueDate(){
        if (book == null) throw new IllegalArgumentException("Book can not be null");
        if (loanDate == null)
            loanDate = LocalDate.now();
        this.dueDate=  this.loanDate.plusDays(book.getMaxLoanDays());
    }
}
