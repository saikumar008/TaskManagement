package com.project.taskmanagement.Service;

import com.project.taskmanagement.DTO.TaskDTO;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface TaskService {
    TaskDTO createTask(TaskDTO taskDTO);
    TaskDTO getTaskById(UUID id);
    List<TaskDTO> getAllTasks();
    List<TaskDTO> getTasksByAssignee(UUID assigneeId);
    TaskDTO updateTask(UUID id, TaskDTO taskDTO);
    void deleteTask(UUID id);
    public TaskDTO partialUpdateTask(UUID id, Map<String, Object> updates);
}

