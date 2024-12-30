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
    private List<Task> assignedTasks;// List of tasks assigned to this user

    public User() {
    }

    public User(UUID id, String name, String email, UserRole role, List<Task> assignedTasks) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.assignedTasks = assignedTasks;
    }



    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public List<Task> getAssignedTasks() {
        return assignedTasks;
    }

    public void setAssignedTasks(List<Task> assignedTasks) {
        this.assignedTasks = assignedTasks;
    }

    //create a builder class for User entity
    public static class UserBuilder {
        private UUID id;
        private String name;
        private String email;
        private UserRole role;
        private List<Task> assignedTasks;

        public UserBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public UserBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder role(UserRole role) {
            this.role = role;
            return this;
        }

        public UserBuilder assignedTasks(List<Task> assignedTasks) {
            this.assignedTasks = assignedTasks;
            return this;
        }

        public User build() {
            return new User(id, name, email, role, assignedTasks);
        }
    }
}

