package com.project.taskmanagement.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "daily_status_reports")
public class DailyStatusReport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id; // Unique identifier for the report

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private User employee; // Reference to the employee submitting the report

    @Column(name = "report_date", nullable = false)
    private LocalDate reportDate; // The date of the report

    @Column(name = "completed_tasks_count", nullable = false)
    private int completedTasksCount; // Number of tasks completed by the employee

    @Column(name = "pending_tasks_count", nullable = false)
    private int pendingTasksCount; // Number of pending tasks

    @Column(name = "delayed_tasks_count", nullable = false)
    private int delayedTasksCount; // Number of delayed tasks

    @Column(name = "pending_reasons", columnDefinition = "TEXT")
    private String pendingReasons; // Reasons for pending tasks

    @Column(name = "delayed_reasons", columnDefinition = "TEXT")
    private String delayedReasons; // Reasons for delayed tasks

    @Column(name = "completion_date")
    private LocalDate completionDate; // Date the task was marked as completed

    @Column(name = "delay_reason", columnDefinition = "TEXT")
    private String delayReason; // Reason for the task delay

}

