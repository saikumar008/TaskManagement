package com.project.taskmanagement.DTO;

import com.project.taskmanagement.ENUM.Priority;
import com.project.taskmanagement.ENUM.TaskStatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class TaskDTO {
    private UUID id;
    private String title;
    private String description;
    private Priority priority;
    private String deadline;
    private TaskStatus status;
    private String progressNotes;
    private UUID createdBy; // ID of the creator
    private LocalDateTime createdAt;
    private LocalDate completionDate;
    private UUID assignee; // ID of the assignee
    private String additionalNotes;
    private List<String> inputSources; // Input source types (e.g., Notebook, Voice, Video)


    public TaskDTO(UUID id, String title, String description, Priority priority, String deadline, TaskStatus status, String progressNotes, UUID createdBy, LocalDateTime createdAt, LocalDate completionDate, UUID assignee, String additionalNotes, List<String> inputSources) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.deadline = deadline;
        this.status = status;
        this.progressNotes = progressNotes;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.completionDate = completionDate;
        this.assignee = assignee;
        this.additionalNotes = additionalNotes;
        this.inputSources = inputSources;
    }

    public TaskDTO() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public String getProgressNotes() {
        return progressNotes;
    }

    public void setProgressNotes(String progressNotes) {
        this.progressNotes = progressNotes;
    }

    public UUID getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UUID createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }

    public UUID getAssignee() {
        return assignee;
    }

    public void setAssignee(UUID assignee) {
        this.assignee = assignee;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }

    public List<String> getInputSources() {
        return inputSources;
    }

    public void setInputSources(List<String> inputSources) {
        this.inputSources = inputSources;
    }

    //create buider class for TaskDTO
    public static class TaskDTOBuilder {
        private UUID id;
        private String title;
        private String description;
        private Priority priority;
        private String deadline;
        private TaskStatus status;
        private String progressNotes;
        private UUID createdBy;
        private LocalDateTime createdAt;
        private LocalDate completionDate;
        private UUID assignee;
        private String additionalNotes;
        private List<String> inputSources;

        public TaskDTOBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public TaskDTOBuilder title(String title) {
            this.title = title;
            return this;
        }

        public TaskDTOBuilder description(String description) {
            this.description = description;
            return this;
        }

        public TaskDTOBuilder priority(Priority priority) {
            this.priority = priority;
            return this;
        }

        public TaskDTOBuilder deadline(String deadline) {
            this.deadline = deadline;
            return this;
        }

        public TaskDTOBuilder status(TaskStatus status) {
            this.status = status;
            return this;
        }

        public TaskDTOBuilder progressNotes(String progressNotes) {
            this.progressNotes = progressNotes;
            return this;
        }

        public TaskDTOBuilder createdBy(UUID createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        public TaskDTOBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public TaskDTOBuilder completionDate(LocalDate completionDate) {
            this.completionDate = completionDate;
            return this;
        }

        public TaskDTOBuilder assignee(UUID assignee) {
            this.assignee = assignee;
            return this;
        }

        public TaskDTOBuilder additionalNotes(String additionalNotes) {
            this.additionalNotes = additionalNotes;
            return this;
        }

        public TaskDTOBuilder inputSources(List<String> inputSources) {
            this.inputSources = inputSources;
            return this;
        }

        public TaskDTO build() {
            return new TaskDTO(id, title, description, priority, deadline, status, progressNotes, createdBy, createdAt, completionDate, assignee, additionalNotes, inputSources);
        }
    }
}

