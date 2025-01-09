package com.project.taskmanagement.DTO;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
//@NoArgsConstructor
//@AllArgsConstructor
@Getter
@Setter
public class TaskCommentDTO {

    private UUID id;
    private String content;
    private UUID taskId;
    private UUID authorId;
    private LocalDateTime timestamp;


    //generate constructor
    public TaskCommentDTO(UUID id, String content, UUID taskId, UUID authorId, LocalDateTime timestamp) {
        this.id = id;
        this.content = content;
        this.taskId = taskId;
        this.authorId = authorId;
        this.timestamp = timestamp;
    }

    public TaskCommentDTO() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UUID getTaskId() {
        return taskId;
    }

    public void setTaskId(UUID taskId) {
        this.taskId = taskId;
    }

    public UUID getAuthorId() {
        return authorId;
    }

    public void setAuthorId(UUID authorId) {
        this.authorId = authorId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }


    public static class TaskCommentDTOBuilder {
        private UUID id;
        private String content;
        private UUID taskId;
        private UUID authorId;
        private LocalDateTime timestamp;

        public TaskCommentDTOBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public TaskCommentDTOBuilder content(String content) {
            this.content = content;
            return this;
        }

        public TaskCommentDTOBuilder taskId(UUID taskId) {
            this.taskId = taskId;
            return this;
        }

        public TaskCommentDTOBuilder authorId(UUID authorId) {
            this.authorId = authorId;
            return this;
        }

        public TaskCommentDTOBuilder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public TaskCommentDTO build() {
            return new TaskCommentDTO(id, content, taskId, authorId, timestamp);
        }
    }

}
