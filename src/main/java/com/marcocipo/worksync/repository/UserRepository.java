package com.marcocipo.worksync.repository;

import com.marcocipo.worksync.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository = Datenzugriffsschicht.
 * Spring Data JPA erstellt die Implementierung automatisch.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Wir brauchen später Login/Registrierung über E-Mail.
     * Optional bedeutet: kann vorhanden sein oder nicht.
     */
    Optional<User> findByEmail(String email);

    /**
     * Für Registrierung: prüfen, ob E-Mail schon existiert.
     */
    boolean existsByEmail(String email);
}
