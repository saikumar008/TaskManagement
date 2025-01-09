package com.project.taskmanagement.Service;

import com.project.taskmanagement.DTO.TaskInputSourceDTO;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface TaskInputSourceService {

    TaskInputSourceDTO createTaskInputSource(TaskInputSourceDTO dto);

    TaskInputSourceDTO getTaskInputSourceById(UUID id);

    List<TaskInputSourceDTO> getAllTaskInputSources();

    TaskInputSourceDTO updateTaskInputSource(UUID id, TaskInputSourceDTO dto);

    TaskInputSourceDTO partialUpdateTaskInputSource(UUID id, Map<String, Object> updates);

    void deleteTaskInputSource(UUID id);

}

