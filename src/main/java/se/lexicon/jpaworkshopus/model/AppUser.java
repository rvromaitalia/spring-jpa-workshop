package se.lexicon.jpaworkshopus.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

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

    @OneToOne
    @JoinColumn(name = "details_id")
    private Details userDetails;
}
