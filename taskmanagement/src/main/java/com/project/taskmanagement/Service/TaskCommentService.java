package com.project.taskmanagement.Service;

import com.project.taskmanagement.DTO.TaskCommentDTO;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface TaskCommentService {
    TaskCommentDTO createTaskComment(TaskCommentDTO taskCommentDTO);

    TaskCommentDTO getTaskCommentById(UUID id);

    List<TaskCommentDTO> getAllTaskComments();

    TaskCommentDTO updateTaskComment(UUID id, TaskCommentDTO taskCommentDTO);

    TaskCommentDTO patchTaskComment(UUID id, Map<String, Object> updates);

    void deleteTaskComment(UUID id);
}

