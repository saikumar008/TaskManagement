package com.project.taskmanagement.ServiceImpl;

import com.project.taskmanagement.DTO.TaskCommentDTO;
import com.project.taskmanagement.Entity.TaskComment;
import com.project.taskmanagement.CustomException.ResourceNotFoundException;
import com.project.taskmanagement.Repository.TaskCommentRepository;
import com.project.taskmanagement.Service.TaskCommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskCommentServiceImpl implements TaskCommentService {

    @Autowired
    private TaskCommentRepository taskCommentRepository;

    @Autowired
    private ModelMapper modelMapper;

    public TaskCommentServiceImpl(TaskCommentRepository taskCommentRepository, ModelMapper modelMapper) {
        this.taskCommentRepository = taskCommentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TaskCommentDTO createTaskComment(TaskCommentDTO taskCommentDTO) {
        TaskComment taskComment = modelMapper.map(taskCommentDTO, TaskComment.class);
        taskComment = taskCommentRepository.save(taskComment);
        return modelMapper.map(taskComment, TaskCommentDTO.class);
    }

    @Override
    public TaskCommentDTO getTaskCommentById(UUID id) {
        TaskComment taskComment = taskCommentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TaskComment not found with id: " + id));
        return modelMapper.map(taskComment, TaskCommentDTO.class);
    }

    @Override
    public List<TaskCommentDTO> getAllTaskComments() {
        return taskCommentRepository.findAll().stream()
                .map(taskComment -> modelMapper.map(taskComment, TaskCommentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public TaskCommentDTO updateTaskComment(UUID id, TaskCommentDTO taskCommentDTO) {
        TaskComment taskComment = taskCommentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TaskComment not found with id: " + id));
        modelMapper.map(taskCommentDTO, taskComment);
        taskComment = taskCommentRepository.save(taskComment);
        return modelMapper.map(taskComment, TaskCommentDTO.class);
    }

    @Override
    public TaskCommentDTO patchTaskComment(UUID id, Map<String, Object> updates) {
        TaskComment taskComment = taskCommentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TaskComment not found with id: " + id));

        TaskComment finalTaskComment = taskComment;
        updates.forEach((key, value) -> {
            switch (key) {
                case "content" -> finalTaskComment.setContent((String) value);
                case "timestamp" -> finalTaskComment.setTimestamp((LocalDateTime) value);
                // Add additional fields if needed
            }
        });

        taskComment = taskCommentRepository.save(taskComment);
        return modelMapper.map(taskComment, TaskCommentDTO.class);
    }

    @Override
    public void deleteTaskComment(UUID id) {
        if (!taskCommentRepository.existsById(id)) {
            throw new ResourceNotFoundException("TaskComment not found with id: " + id);
        }
        taskCommentRepository.deleteById(id);
    }
}

