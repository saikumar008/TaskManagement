package com.project.taskmanagement.DTO;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Data
@Builder
//@NoArgsConstructor
//@AllArgsConstructor
@Getter
@Setter
public class TaskInputSourceDTO {

    private UUID id;

    @NotBlank(message = "Source type must not be empty.")
    private String sourceType;

    @NotBlank(message = "File URL must not be empty.")
    private String fileUrl;

    private String processedText;

    @NotNull(message = "Task reference is required.")
    private UUID taskId;

    private UUID taskCommentId;

    //generate Constructor
    public TaskInputSourceDTO(UUID id, String sourceType, String fileUrl, String processedText, UUID taskId, UUID taskCommentId) {
        this.id = id;
        this.sourceType = sourceType;
        this.fileUrl = fileUrl;
        this.processedText = processedText;
        this.taskId = taskId;
        this.taskCommentId = taskCommentId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public @NotBlank(message = "Source type must not be empty.") String getSourceType() {
        return sourceType;
    }

    public void setSourceType(@NotBlank(message = "Source type must not be empty.") String sourceType) {
        this.sourceType = sourceType;
    }

    public @NotBlank(message = "File URL must not be empty.") String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(@NotBlank(message = "File URL must not be empty.") String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getProcessedText() {
        return processedText;
    }

    public void setProcessedText(String processedText) {
        this.processedText = processedText;
    }

    public @NotNull(message = "Task reference is required.") UUID getTaskId() {
        return taskId;
    }

    public void setTaskId(@NotNull(message = "Task reference is required.") UUID taskId) {
        this.taskId = taskId;
    }

    public UUID getTaskCommentId() {
        return taskCommentId;
    }

    public void setTaskCommentId(UUID taskCommentId) {
        this.taskCommentId = taskCommentId;
    }

    public TaskInputSourceDTO() {
    }

    //generate builder class
    public static class TaskInputSourceDTOBuilder {
        private UUID id;
        private String sourceType;
        private String fileUrl;
        private String processedText;
        private UUID taskId;
        private UUID taskCommentId;

        TaskInputSourceDTOBuilder() {
        }

        public TaskInputSourceDTO.TaskInputSourceDTOBuilder id(final UUID id) {
            this.id = id;
            return this;
        }

        public TaskInputSourceDTO.TaskInputSourceDTOBuilder sourceType(final String sourceType) {
            this.sourceType = sourceType;
            return this;
        }

        public TaskInputSourceDTO.TaskInputSourceDTOBuilder fileUrl(final String fileUrl) {
            this.fileUrl = fileUrl;
            return this;
        }

        public TaskInputSourceDTO.TaskInputSourceDTOBuilder processedText(final String processedText) {
            this.processedText = processedText;
            return this;
        }

        public TaskInputSourceDTO.TaskInputSourceDTOBuilder taskId(final UUID taskId) {
            this.taskId = taskId;
            return this;
        }

        public TaskInputSourceDTO.TaskInputSourceDTOBuilder taskCommentId(final UUID taskCommentId) {
            this.taskCommentId = taskCommentId;
            return this;
        }

        public TaskInputSourceDTO build() {
            return new TaskInputSourceDTO(this.id, this.sourceType, this.fileUrl, this.processedText, this.taskId, this.taskCommentId);
        }

        public String toString() {
            return "TaskInputSourceDTO.TaskInputSourceDTOBuilder(id=" + this.id + ", sourceType=" + this.sourceType + ", fileUrl=" + this.fileUrl + ", processedText=" + this.processedText + ", taskId=" + this.taskId + ", taskCommentId=" + this.taskCommentId + ")";
        }
    }

}

