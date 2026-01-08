package com.marcocipo.worksync.service;

import com.marcocipo.worksync.domain.User;
import com.marcocipo.worksync.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

/**
 * CurrentUserService (nur als Übergangslösung für DEV). TODO Nacher raus aus projekt?
 *
 * Idee:
 * - Wir lesen den Header "X-User-Id" aus dem HTTP Request.
 * - Dann laden wir den User aus der Datenbank.
 *
 * Warum?
 * - So können wir Permissions (Gruppenrechte, Assignment etc.) implementieren,
 *   bevor wir JWT/Auth fertig haben.
 *
 * Später:
 * - Wird diese Logik durch JWT ersetzt (SecurityContext liefert dann den User).
 */
@Service
public class CurrentUserService {

    private final UserRepository userRepository;

    public CurrentUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Liefert den aktuellen User oder wirft eine Exception, wenn:
     * - Header fehlt
     * - Header keine Zahl ist
     * - User nicht existiert
     */
    public User requireCurrentUser(HttpServletRequest request) {
        String raw = request.getHeader("X-User-Id");

        // Header muss vorhanden sein
        if (raw == null || raw.isBlank()) {
            throw new IllegalArgumentException("Missing header: X-User-Id");
        }

        // Header muss eine Long-Zahl sein
        Long userId;
        try {
            userId = Long.parseLong(raw);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Header X-User-Id must be a number");
        }

        // User muss existieren
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
    }
}
