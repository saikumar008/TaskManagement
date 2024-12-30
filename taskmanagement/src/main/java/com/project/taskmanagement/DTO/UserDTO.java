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
}

