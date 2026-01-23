package se.lexicon.jpaworkshopus.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;
@ToString
@NoArgsConstructor
@Entity
public class Author {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(nullable = false, length = 20)
    String firstName;

    @Column(nullable = false, length = 20)
    String lastName;

    @ManyToMany(mappedBy = "authors")
    private Set<Book> writtenBooks = new HashSet<>();
}
