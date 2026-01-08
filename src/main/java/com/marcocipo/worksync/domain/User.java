package com.marcocipo.worksync.domain;

import jakarta.persistence.*;


import java.util.HashSet;
import java.util.Set;

/**
 * User-Entity (DB-Modell).
 *
 * Wichtig:
 * - Diese Entity ist NICHT das Objekt, das du direkt über die API zurückgibst.
 *   Dafür nutzen wir später DTOs (Data Transfer Objects).
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // E-Mail soll eindeutig sein
    @Column(nullable = false, unique = true)
    private String email;

    // Passwort wird später gehasht gespeichert
    @Column(nullable = false)
    private String passwordHash;

    // Enum wird als String gespeichert (lesbarer als ordinal)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.USER;

    /**
     * Gruppen-Mitgliedschaft:
     * - Many-to-Many, weil: ein User kann in vielen Gruppen sein,
     *   und eine Gruppe hat viele User.
     */
    @ManyToMany(mappedBy = "members")
    private Set<Group> groups = new HashSet<>();

    // --- Getter/Setter (für JPA notwendig) ---
    public Long getId() { return id; }
    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }
    public Role getRole() { return role; }
    public Set<Group> getGroups() { return groups; }

    public void setEmail(String email) { this.email = email; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public void setRole(Role role) { this.role = role; }
}
