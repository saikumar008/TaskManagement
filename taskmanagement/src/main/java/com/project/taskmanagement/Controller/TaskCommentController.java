package com.project.taskmanagement.Controller;

import com.project.taskmanagement.DTO.TaskCommentDTO;
import com.project.taskmanagement.Service.TaskCommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/task-comments")
public class TaskCommentController {

    private final TaskCommentService taskCommentService;

    public TaskCommentController(TaskCommentService taskCommentService) {
        this.taskCommentService = taskCommentService;
    }

    @PostMapping
    public ResponseEntity<TaskCommentDTO> createTaskComment(@RequestBody TaskCommentDTO taskCommentDTO) {
        TaskCommentDTO createdTaskComment = taskCommentService.createTaskComment(taskCommentDTO);
        return ResponseEntity.ok(createdTaskComment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskCommentDTO> getTaskCommentById(@PathVariable UUID id) {
        TaskCommentDTO taskComment = taskCommentService.getTaskCommentById(id);
        return ResponseEntity.ok(taskComment);
    }

    @GetMapping
    public ResponseEntity<List<TaskCommentDTO>> getAllTaskComments() {
        List<TaskCommentDTO> taskComments = taskCommentService.getAllTaskComments();
        return ResponseEntity.ok(taskComments);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskCommentDTO> updateTaskComment(@PathVariable UUID id, @RequestBody TaskCommentDTO taskCommentDTO) {
        TaskCommentDTO updatedTaskComment = taskCommentService.updateTaskComment(id, taskCommentDTO);
        return ResponseEntity.ok(updatedTaskComment);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskCommentDTO> patchTaskComment(@PathVariable UUID id, @RequestBody Map<String, Object> updates) {
        TaskCommentDTO patchedTaskComment = taskCommentService.patchTaskComment(id, updates);
        return ResponseEntity.ok(patchedTaskComment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskComment(@PathVariable UUID id) {
        taskCommentService.deleteTaskComment(id);
        return ResponseEntity.noContent().build();
    }
}

