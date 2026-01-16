package se.lexicon.jpaworkshopus.repo;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.jpaworkshopus.model.AppUser;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AppUserRepo extends JpaRepository<AppUser, Integer> {
    Optional<AppUser> findByUserName(String userName);
    List<AppUser> findByRegDateBetween(LocalDate start, LocalDate end);
    Optional<AppUser> findByUserDetails_Id(Integer detailsId);
    List<AppUser> findByUserDetails_EmailIgnoreCase(String email);
}
