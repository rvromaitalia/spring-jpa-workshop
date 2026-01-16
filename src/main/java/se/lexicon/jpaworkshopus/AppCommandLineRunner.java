package se.lexicon.jpaworkshopus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.lexicon.jpaworkshopus.repo.AppUserRepo;

@Component
public class AppCommandLineRunner implements CommandLineRunner {
    AppUserRepo appUserRepo;

    @Autowired
    public AppCommandLineRunner(AppUserRepo appUserRepo) {
        this.appUserRepo = appUserRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        appUserRepo.findAll().forEach(System.out::println);

    }
}
