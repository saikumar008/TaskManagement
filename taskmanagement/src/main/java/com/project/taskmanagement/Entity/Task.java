package com.project.taskmanagement.Entity;

import com.project.taskmanagement.ENUM.Priority;
import com.project.taskmanagement.ENUM.TaskStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "task_id", updatable = false, nullable = false)
    private UUID id;

    @NotBlank(message = "Task title is required.")
    @Size(max = 100, message = "Task title should not exceed 100 characters.")
    @Column(name = "task_title", nullable = false, length = 100)
    private String title;

    @Size(max = 500, message = "Task description should not exceed 500 characters.")
    @Column(name = "task_description", length = 500)
    private String description;

    @Column(name = "priority", length = 20)
    private Priority priority; // e.g., "High", "Medium", "Low"

    @Column(name = "deadline")
    private String deadline; // Optional deadline in ISO 8601 format

    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskInputSource> inputSources; // Notebook, Voice, Video inputs

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TaskStatus status = TaskStatus.PENDING; // Default status is Pending

    @Column(name = "progress_notes")
    private String progressNotes;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "created_by")
    private User createdBy;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "completion_date")
    private LocalDate completionDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignee_id") // Foreign key to User
    private User assignee;  // The user assigned to the task

    @Lob
    @Column(name = "additional_notes")
    private String additionalNotes;

//    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
//    private List<Attachment> attachments;

//    @Column(name = "emotional_tone", nullable = true)
//    private String emotionalTone; // Emotional annotation

    // Other fields, constructors, and methods can be added as needed.


    public Task(UUID id, String title, String description, Priority priority, String deadline, List<TaskInputSource> inputSources, TaskStatus status, String progressNotes, User createdBy, LocalDateTime createdAt, LocalDate completionDate, User assignee, String additionalNotes) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.deadline = deadline;
        this.inputSources = inputSources;
        this.status = status;
        this.progressNotes = progressNotes;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.completionDate = completionDate;
        this.assignee = assignee;
        this.additionalNotes = additionalNotes;
    }

    public Task() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public @NotBlank(message = "Task title is required.") @Size(max = 100, message = "Task title should not exceed 100 characters.") String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank(message = "Task title is required.") @Size(max = 100, message = "Task title should not exceed 100 characters.") String title) {
        this.title = title;
    }

    public @Size(max = 500, message = "Task description should not exceed 500 characters.") String getDescription() {
        return description;
    }

    public void setDescription(@Size(max = 500, message = "Task description should not exceed 500 characters.") String description) {
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

    public List<TaskInputSource> getInputSources() {
        return inputSources;
    }

    public void setInputSources(List<TaskInputSource> inputSources) {
        this.inputSources = inputSources;
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

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
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

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }

    //create a builder class for Task entity
    public static class TaskBuilder {
        private UUID id;
        private String title;
        private String description;
        private Priority priority;
        private String deadline;
        private List<TaskInputSource> inputSources;
        private TaskStatus status;
        private String progressNotes;
        private User createdBy;
        private LocalDateTime createdAt;
        private LocalDate completionDate;
        private User assignee;
        private String additionalNotes;

        public TaskBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public TaskBuilder title(String title) {
            this.title = title;
            return this;
        }

        public TaskBuilder description(String description) {
            this.description = description;
            return this;
        }

        public TaskBuilder priority(Priority priority) {
            this.priority = priority;
            return this;
        }

        public TaskBuilder deadline(String deadline) {
            this.deadline = deadline;
            return this;
        }

        public TaskBuilder inputSources(List<TaskInputSource> inputSources) {
            this.inputSources = inputSources;
            return this;
        }

        public TaskBuilder status(TaskStatus status) {
            this.status = status;
            return this;
        }

        public TaskBuilder progressNotes(String progressNotes) {
            this.progressNotes = progressNotes;
            return this;
        }

        public TaskBuilder createdBy(User createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        public TaskBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public TaskBuilder completionDate(LocalDate completionDate) {
            this.completionDate = completionDate;
            return this;
        }

        public TaskBuilder assignee(User assignee) {
            this.assignee = assignee;
            return this;
        }

        public TaskBuilder additionalNotes(String additionalNotes) {
            this.additionalNotes = additionalNotes;
            return this;
        }

        public Task build() {
            return new Task(id, title, description, priority, deadline, inputSources, status, progressNotes, createdBy, createdAt, completionDate, assignee, additionalNotes);
        }
    }
}



