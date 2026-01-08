package com.marcocipo.worksync.service;

import com.marcocipo.worksync.domain.Group;
import com.marcocipo.worksync.domain.User;
import com.marcocipo.worksync.repository.GroupRepository;
import org.springframework.stereotype.Service;

@Service
public class MembershipService {

    private final GroupRepository groupRepository;

    public MembershipService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public boolean isMember(User user, Group group) {
        // DB-Check statt Lazy Collection Zugriff
        return groupRepository.isMember(group.getId(), user.getId());
    }
}
