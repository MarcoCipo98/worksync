package com.marcocipo.worksync.web.dto;

import com.marcocipo.worksync.domain.Priority;
import com.marcocipo.worksync.domain.WorkItemStatus;

import java.time.Instant;

/**
 * DTO für die Ausgabe eines WorkItems über die API.
 */
public class WorkItemResponse {
    public Long id;
    public String title;
    public String description;
    public WorkItemStatus status;
    public Priority priority;

    public Long creatorId;
    public Long assigneeId;
    public Long groupId;

    public Instant createdAt;
    public Instant updatedAt;
}
