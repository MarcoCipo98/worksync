package com.marcocipo.worksync.web;

import com.marcocipo.worksync.domain.User;
import com.marcocipo.worksync.domain.WorkItem;
import com.marcocipo.worksync.service.CurrentUserService;
import com.marcocipo.worksync.service.WorkItemService;
import com.marcocipo.worksync.web.dto.CreateWorkItemRequest;
import com.marcocipo.worksync.web.dto.WorkItemResponse;
import com.marcocipo.worksync.web.mapper.WorkItemMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller für WorkItems.
 * Kümmert sich nur um HTTP/JSON; Business-Regeln sind im Service.
 */
@RestController
@RequestMapping("/api/work-items")
public class WorkItemController {

    private final CurrentUserService currentUserService;
    private final WorkItemService workItemService;
    private final WorkItemMapper workItemMapper;


    public WorkItemController(
            CurrentUserService currentUserService,
            WorkItemService workItemService,
            WorkItemMapper workItemMapper

    ) {
        this.currentUserService = currentUserService;
        this.workItemService = workItemService;
        this.workItemMapper = workItemMapper;

    }

    @PostMapping
    public WorkItemResponse create(@Valid @RequestBody CreateWorkItemRequest req, HttpServletRequest httpRequest) {
        // "aktuellen User" für dev über Header holen
        User currentUser = currentUserService.requireCurrentUser(httpRequest);

        WorkItem created = workItemService.create(
                currentUser,
                req.groupId,
                req.title,
                req.description,
                req.priority
        );

        return workItemMapper.toResponse(created);
    }

    @GetMapping
    public List<WorkItemResponse> listByGroup(
            @RequestParam Long groupId,
            HttpServletRequest httpRequest
    ) {
        User currentUser = currentUserService.requireCurrentUser(httpRequest);

        return workItemService.listByGroup(currentUser, groupId).stream()
                .map(workItemMapper::toResponse)
                .toList();
    }
}
