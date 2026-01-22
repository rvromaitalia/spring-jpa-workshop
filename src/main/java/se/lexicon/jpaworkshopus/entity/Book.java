package se.lexicon.jpaworkshopus.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Entity
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int id;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private int maxLoanDays = 20;

    @Column(length = 35)
    private String author;

    public Book(String isbn, int maxLoanDays, String title) {
        this.isbn = isbn;
        this.maxLoanDays = maxLoanDays;
        this.title = title;
    }
}
