package com.marcocipo.worksync.domain;

import jakarta.persistence.*;
import java.time.Instant;

/**
 * WorkItem entspricht einem Jira-Ticket im MVP.
 */
@Entity
@Table(name = "work_items")
public class WorkItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Titel ist Pflicht
    @Column(nullable = false)
    private String title;


    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WorkItemStatus status = WorkItemStatus.OFFEN;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priority priority = Priority.MEDIUM;

    /**
     * Creator: Ersteller bleibt immer gesetzt.
     * Many-to-One, weil: viele WorkItems können vom gleichen User erstellt werden.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;

    /**
     * Assignee: Bearbeiter, kann null sein (noch niemand zugewiesen).
     */
    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private User assignee;

    /**
     * WorkItem gehört zu genau einer Gruppe.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    @Column(nullable = false)
    private Instant updatedAt = Instant.now();

    /**
     * wird automatisch aufgerufen bevor was in die db geht, so wird updatedAt immer auf now gesetzt
     */
    @PreUpdate
    public void onUpdate() {
        this.updatedAt = Instant.now();
    }

    // --- Getter/Setter ---
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public WorkItemStatus getStatus() { return status; }
    public Priority getPriority() { return priority; }
    public User getCreator() { return creator; }
    public User getAssignee() { return assignee; }
    public Group getGroup() { return group; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }

    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setStatus(WorkItemStatus status) { this.status = status; }
    public void setPriority(Priority priority) { this.priority = priority; }
    public void setCreator(User creator) { this.creator = creator; }
    public void setAssignee(User assignee) { this.assignee = assignee; }
    public void setGroup(Group group) { this.group = group; }
}
