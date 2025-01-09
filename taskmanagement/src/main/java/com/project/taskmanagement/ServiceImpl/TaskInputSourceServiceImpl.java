package com.project.taskmanagement.ServiceImpl;

import com.project.taskmanagement.DTO.TaskInputSourceDTO;
import com.project.taskmanagement.Entity.Task;
import com.project.taskmanagement.Entity.TaskComment;
import com.project.taskmanagement.Entity.TaskInputSource;
import com.project.taskmanagement.Repository.TaskInputSourceRepository;
import com.project.taskmanagement.Repository.TaskRepository;
import com.project.taskmanagement.Repository.TaskCommentRepository;
import com.project.taskmanagement.Service.TaskInputSourceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskInputSourceServiceImpl implements TaskInputSourceService {

    @Autowired
    private TaskInputSourceRepository taskInputSourceRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskCommentRepository taskCommentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TaskInputSourceDTO createTaskInputSource(TaskInputSourceDTO dto) {
        Task task = taskRepository.findById(dto.getTaskId())
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + dto.getTaskId()));

        TaskComment taskComment = null;
        if (dto.getTaskCommentId() != null) {
            taskComment = taskCommentRepository.findById(dto.getTaskCommentId())
                    .orElseThrow(() -> new RuntimeException("TaskComment not found with id: " + dto.getTaskCommentId()));
        }

        TaskInputSource taskInputSource = modelMapper.map(dto, TaskInputSource.class);
        taskInputSource.setTask(task);
        taskInputSource.setTaskComment(taskComment);

        TaskInputSource savedInputSource = taskInputSourceRepository.save(taskInputSource);
        return modelMapper.map(savedInputSource, TaskInputSourceDTO.class);
    }

    @Override
    public TaskInputSourceDTO getTaskInputSourceById(UUID id) {
        TaskInputSource taskInputSource = taskInputSourceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TaskInputSource not found with id: " + id));
        return modelMapper.map(taskInputSource, TaskInputSourceDTO.class);
    }

    @Override
    public List<TaskInputSourceDTO> getAllTaskInputSources() {
        return taskInputSourceRepository.findAll()
                .stream()
                .map(inputSource -> modelMapper.map(inputSource, TaskInputSourceDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public TaskInputSourceDTO updateTaskInputSource(UUID id, TaskInputSourceDTO dto) {
        TaskInputSource existingInputSource = taskInputSourceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TaskInputSource not found with id: " + id));

        modelMapper.map(dto, existingInputSource);

        Task task = taskRepository.findById(dto.getTaskId())
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + dto.getTaskId()));
        existingInputSource.setTask(task);

        if (dto.getTaskCommentId() != null) {
            TaskComment taskComment = taskCommentRepository.findById(dto.getTaskCommentId())
                    .orElseThrow(() -> new RuntimeException("TaskComment not found with id: " + dto.getTaskCommentId()));
            existingInputSource.setTaskComment(taskComment);
        }

        TaskInputSource updatedInputSource = taskInputSourceRepository.save(existingInputSource);
        return modelMapper.map(updatedInputSource, TaskInputSourceDTO.class);
    }

    @Override
    public TaskInputSourceDTO partialUpdateTaskInputSource(UUID id, Map<String, Object> updates) {
        TaskInputSource existingInputSource = taskInputSourceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TaskInputSource not found with id: " + id));

        updates.forEach((key, value) -> {
            switch (key) {
                case "sourceType" -> existingInputSource.setSourceType((String) value);
                case "fileUrl" -> existingInputSource.setFileUrl((String) value);
                case "processedText" -> existingInputSource.setProcessedText((String) value);
                case "taskId" -> {
                    Task task = taskRepository.findById(UUID.fromString((String) value))
                            .orElseThrow(() -> new RuntimeException("Task not found with id: " + value));
                    existingInputSource.setTask(task);
                }
                case "taskCommentId" -> {
                    TaskComment taskComment = taskCommentRepository.findById(UUID.fromString((String) value))
                            .orElseThrow(() -> new RuntimeException("TaskComment not found with id: " + value));
                    existingInputSource.setTaskComment(taskComment);
                }
            }
        });

        TaskInputSource updatedInputSource = taskInputSourceRepository.save(existingInputSource);
        return modelMapper.map(updatedInputSource, TaskInputSourceDTO.class);
    }

    @Override
    public void deleteTaskInputSource(UUID id) {
        if (!taskInputSourceRepository.existsById(id)) {
            throw new RuntimeException("TaskInputSource not found with id: " + id);
        }
        taskInputSourceRepository.deleteById(id);
    }
}

