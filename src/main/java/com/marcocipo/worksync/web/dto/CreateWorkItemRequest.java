package com.marcocipo.worksync.web.dto;

import com.marcocipo.worksync.domain.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO für das Erstellen eines WorkItems.
 * DTOs sind reine API-Objekte (JSON rein/raus) und werden NICHT direkt in die DB gespeichert.
 */
public class CreateWorkItemRequest {

    @NotBlank
    public String title;

    public String description;

    @NotNull
    public Long groupId;

    // Optional: wenn null, bleibt Default aus Entity (MEDIUM)
    public Priority priority;
}
