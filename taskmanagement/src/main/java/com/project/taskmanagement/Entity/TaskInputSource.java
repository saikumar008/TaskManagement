package com.project.taskmanagement.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "task_input_sources")
public class TaskInputSource {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "source_id", updatable = false, nullable = false)
    private UUID id;

    @NotBlank(message = "Source type must not be empty.")
    @Column(name = "source_type", nullable = false, length = 50)
    private String sourceType; // e.g., "Notebook", "Voice", "Video"

    @NotBlank(message = "File URL must not be empty.")
    @Column(name = "file_url", nullable = false)
    private String fileUrl; // URL where the uploaded file is stored

    @Column(name = "processed_text")
    private String processedText; // Extracted text from the uploaded file

    @NotNull(message = "Task reference is required.")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "task_id", nullable = false)
    private Task task; // Associated Task

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_comment_id")
    private TaskComment taskComment;

    //generate constructor
    public TaskInputSource(String sourceType, String fileUrl, String processedText, Task task, TaskComment taskComment) {
        this.sourceType = sourceType;
        this.fileUrl = fileUrl;
        this.processedText = processedText;
        this.task = task;
        this.taskComment = taskComment;
    }

    public TaskInputSource() {
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

    public @NotNull(message = "Task reference is required.") Task getTask() {
        return task;
    }

    public void setTask(@NotNull(message = "Task reference is required.") Task task) {
        this.task = task;
    }

    public TaskComment getTaskComment() {
        return taskComment;
    }

    public void setTaskComment(TaskComment taskComment) {
        this.taskComment = taskComment;
    }

    //generate builder class
    public static class TaskInputSourceBuilder {
        private UUID id;
        private String sourceType;
        private String fileUrl;
        private String processedText;
        private Task task;
        private TaskComment taskComment;

        TaskInputSourceBuilder() {
        }

        public TaskInputSource.TaskInputSourceBuilder id(final UUID id) {
            this.id = id;
            return this;
        }

        public TaskInputSource.TaskInputSourceBuilder sourceType(final String sourceType) {
            this.sourceType = sourceType;
            return this;
        }

        public TaskInputSource.TaskInputSourceBuilder fileUrl(final String fileUrl) {
            this.fileUrl = fileUrl;
            return this;
        }

        public TaskInputSource.TaskInputSourceBuilder processedText(final String processedText) {
            this.processedText = processedText;
            return this;
        }

        public TaskInputSource.TaskInputSourceBuilder task(final Task task) {
            this.task = task;
            return this;
        }

        public TaskInputSource.TaskInputSourceBuilder taskComment(final TaskComment taskComment) {
            this.taskComment = taskComment;
            return this;
        }

        public TaskInputSource build() {
            return new TaskInputSource(this.sourceType, this.fileUrl, this.processedText, this.task, this.taskComment);
        }

        public String toString() {
            return "TaskInputSource.TaskInputSourceBuilder(id=" + this.id + ", sourceType=" + this.sourceType + ", fileUrl=" + this.fileUrl + ", processedText=" + this.processedText + ", task=" + this.task + ", taskComment=" + this.taskComment + ")";
        }
    }
}

