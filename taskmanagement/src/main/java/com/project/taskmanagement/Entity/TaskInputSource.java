package com.project.taskmanagement.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

}

