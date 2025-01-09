package com.project.taskmanagement.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "task_comments")
public class TaskComment {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    @OneToMany(mappedBy = "taskComment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskInputSource> inputsource;

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

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public List<TaskInputSource> getInputsource() {
        return inputsource;
    }

    public void setInputsource(List<TaskInputSource> inputsource) {
        this.inputsource = inputsource;
    }

    public TaskComment(UUID id, String content, Task task, User author, LocalDateTime timestamp) {
        this.id = id;
        this.content = content;
        this.task = task;
        this.author = author;
        this.timestamp = timestamp;
    }


    public static class TaskCommentBuilder {
        private UUID id;
        private String content;
        private Task task;
        private User author;
        private LocalDateTime timestamp;

        public TaskCommentBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public TaskCommentBuilder content(String content) {
            this.content = content;
            return this;
        }

        public TaskCommentBuilder task(Task task) {
            this.task = task;
            return this;
        }

        public TaskCommentBuilder author(User author) {
            this.author = author;
            return this;
        }

        public TaskCommentBuilder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public TaskComment build() {
            return new TaskComment(id, content, task, author, timestamp);
        }
    }

}

