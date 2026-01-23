package se.lexicon.jpaworkshopus.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Represents an application user in the system.
 * <p>
 * Each AppUser has login credentials and is associated with exactly one
 * {@link Details} entity that stores personal information.
 * This entity is the owning side of the one-to-one relationship.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    @Column(nullable = false, length = 200, unique = true)
    private String userName;

    @Column(nullable = false, length = 200)
    private String password;

    private LocalDate regDate;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "details_id")
    private Details userDetails;

    @OneToMany(mappedBy = "borrower", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookLoan> bookLoans  =  new ArrayList<>();

    public void addBookLoan(BookLoan loan){
        if(loan == null)  return;

        bookLoans.add(loan);
        loan.setBorrower(this);
    }

    public void removeLoan(BookLoan loan) {
        if (loan == null) return;

        bookLoans.remove(loan);
        loan.setBorrower(null);
    }
}






