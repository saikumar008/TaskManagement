package com.project.taskmanagement.DTO;


import com.project.taskmanagement.ENUM.UserRole;
import com.project.taskmanagement.Entity.Task;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class UserDTO {
    private UUID id;
    private String name;
    private String email;
    private UserRole role;
    private List<Task> assignedTasks;

    public UserDTO() {
    }

    //constructor with all fields
    public UserDTO(UUID id, String name, String email, UserRole role, List<Task> assignedTasks) {
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

    // generate builder class for UserDTO
    public static class UserDTOBuilder {
        private UUID id;
        private String name;
        private String email;
        private UserRole role;
        private List<Task> assignedTasks;

        public UserDTOBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public UserDTOBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserDTOBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserDTOBuilder role(UserRole role) {
            this.role = role;
            return this;
        }

        public UserDTOBuilder assignedTasks(List<Task> assignedTasks) {
            this.assignedTasks = assignedTasks;
            return this;
        }

        public UserDTO build() {
            return new UserDTO(id, name, email, role, assignedTasks);
        }
    }
}

