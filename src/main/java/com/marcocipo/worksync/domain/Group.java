package com.marcocipo.worksync.domain;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Sichtbarkeit-Regel:
 * WorkItems gehören zu genau einer Gruppe.
 * Nur Mitglieder dieser Gruppe dürfen die WorkItems sehen .
 */
@Entity
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, unique = true)
    private String name;

    /**
     * Many-to-Many: Mitglieder der Gruppe.
     * Wir definieren hier die Join-Tabelle.
     */
    @ManyToMany
    @JoinTable(
            name = "group_members",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> members = new HashSet<>();

    // --- Getter/Setter ---
    public Long getId() { return id; }
    public String getName() { return name; }
    public Set<User> getMembers() { return members; }

    public void setName(String name) { this.name = name; }
}
