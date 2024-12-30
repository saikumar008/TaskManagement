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
@AllArgsConstructor
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

}



