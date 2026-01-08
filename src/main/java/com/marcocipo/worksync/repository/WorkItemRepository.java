package com.marcocipo.worksync.repository;

import com.marcocipo.worksync.domain.WorkItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Zugriff auf WorkItems (Tickets).
 */
public interface WorkItemRepository extends JpaRepository<WorkItem, Long> {

    /**
     * Alle WorkItems einer Gruppe.
     * (Sichtbarkeit: User sieht WorkItems nur aus Gruppen, in denen er Mitglied ist.)
     */
    List<WorkItem> findAllByGroup_Id(Long groupId);
}
