package se.lexicon.jpaworkshopus.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

/**
 * Stores personal and contact information for an {@link AppUser}.
 * <p>
 * This entity is linked one-to-one with AppUser and is the inverse
 * side of the relationship.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Details {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    @Column(unique = true, nullable = false, length = 200)
    private String email;

    @Setter
    @Column(nullable = false, length = 200)
    private String name;

    private LocalDate birthDate;

    @OneToOne(mappedBy = "userDetails")
    private AppUser appUser;
}

