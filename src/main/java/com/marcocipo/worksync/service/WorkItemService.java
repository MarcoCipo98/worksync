package com.marcocipo.worksync.service;

import com.marcocipo.worksync.domain.Group;
import com.marcocipo.worksync.domain.Priority;
import com.marcocipo.worksync.domain.User;
import com.marcocipo.worksync.domain.WorkItem;
import com.marcocipo.worksync.repository.GroupRepository;
import com.marcocipo.worksync.repository.WorkItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * WorkItemService enthält Business-Logik:
 * - Berechtigungen (Gruppenmitgliedschaft)
 * - Erstellung
 * - später: Updates, Assignments, Statuswechsel, Filter
 */
@Service
public class WorkItemService {

    private final WorkItemRepository workItemRepository;
    private final GroupRepository groupRepository;
    private final MembershipService membershipService;

    public WorkItemService(
            WorkItemRepository workItemRepository,
            GroupRepository groupRepository,
            MembershipService membershipService
    ) {
        this.workItemRepository = workItemRepository;
        this.groupRepository = groupRepository;
        this.membershipService = membershipService;
    }

    public WorkItem create(User creator, Long groupId, String title, String description, Priority priority) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Group not found: " + groupId));

        // Permission: nur Gruppenmitglieder dürfen WorkItems in der Gruppe erstellen
        if (!membershipService.isMember(creator, group)) {
            throw new IllegalArgumentException("User is not member of group: " + group.getName());
        }

        WorkItem wi = new WorkItem();
        wi.setTitle(title);
        wi.setDescription(description);
        wi.setGroup(group);
        wi.setCreator(creator);

        // Priorität ist optional. Wenn null, bleibt Default (MEDIUM) aus WorkItem-Entity.
        if (priority != null) {
            wi.setPriority(priority);
        }

        return workItemRepository.save(wi);
    }

    public List<WorkItem> listByGroup(User user, Long groupId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Group not found: " + groupId));

        // Permission: nur Gruppenmitglieder dürfen listen
        if (!membershipService.isMember(user, group)) {
            throw new SecurityException("User is not member of group: " + group.getName());
        }

        return workItemRepository.findAllByGroup_Id(groupId);
    }


}
