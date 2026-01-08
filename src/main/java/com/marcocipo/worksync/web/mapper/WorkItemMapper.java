package com.marcocipo.worksync.web.mapper;

import com.marcocipo.worksync.domain.WorkItem;
import com.marcocipo.worksync.web.dto.WorkItemResponse;
import org.springframework.stereotype.Component;

/**
 * Mapper wandelt Entities (DB) in DTOs (API) um.
 * Vorteil: Controller bleibt frei von Mapping-Logik.
 */
@Component
public class WorkItemMapper {

    public WorkItemResponse toResponse(WorkItem wi) {
        WorkItemResponse dto = new WorkItemResponse();
        dto.id = wi.getId();
        dto.title = wi.getTitle();
        dto.description = wi.getDescription();
        dto.status = wi.getStatus();
        dto.priority = wi.getPriority();

        dto.creatorId = wi.getCreator().getId();
        dto.assigneeId = (wi.getAssignee() != null) ? wi.getAssignee().getId() : null;
        dto.groupId = wi.getGroup().getId();

        dto.createdAt = wi.getCreatedAt();
        dto.updatedAt = wi.getUpdatedAt();
        return dto;
    }
}
