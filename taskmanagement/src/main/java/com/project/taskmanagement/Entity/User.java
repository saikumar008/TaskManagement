package com.project.taskmanagement.Entity;

import com.project.taskmanagement.ENUM.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;  // Unique identifier for each user

    @Column(name = "name", nullable = false)
    private String name;  // Name of the user

    @Column(name = "email", nullable = false, unique = true)
    private String email;  // Email address of the user

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserRole role;  // Role of the user (e.g., Employee, Admin)

    @OneToMany(mappedBy = "assignee")
    private List<Task> assignedTasks;  // List of tasks assigned to this user

}

