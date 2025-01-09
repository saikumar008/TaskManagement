package com.project.taskmanagement.Controller;

import com.project.taskmanagement.DTO.TaskInputSourceDTO;
import com.project.taskmanagement.Service.TaskInputSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/task-input-sources")
public class TaskInputSourceController {

    @Autowired
    private TaskInputSourceService taskInputSourceService;

    @PostMapping("/createTaskInputSource")
    public ResponseEntity<TaskInputSourceDTO> createTaskInputSource(@RequestBody TaskInputSourceDTO dto) {
        return ResponseEntity.ok(taskInputSourceService.createTaskInputSource(dto));
    }

    @GetMapping("/{id}/getTaskInputSourceById")
    public ResponseEntity<TaskInputSourceDTO> getTaskInputSourceById(@PathVariable UUID id) {
        return ResponseEntity.ok(taskInputSourceService.getTaskInputSourceById(id));
    }

    @GetMapping("/getAllTaskInputSources")
    public ResponseEntity<List<TaskInputSourceDTO>> getAllTaskInputSources() {
        return ResponseEntity.ok(taskInputSourceService.getAllTaskInputSources());
    }

    @PutMapping("/{id}/updateTaskInputtSource")
    public ResponseEntity<TaskInputSourceDTO> updateTaskInputSource(@PathVariable UUID id, @RequestBody TaskInputSourceDTO dto) {
        return ResponseEntity.ok(taskInputSourceService.updateTaskInputSource(id, dto));
    }

    @PatchMapping("/{id}/partialUpdateTaskInputSource")
    public ResponseEntity<TaskInputSourceDTO> partialUpdateTaskInputSource(@PathVariable UUID id, @RequestBody Map<String, Object> updates) {
        return ResponseEntity.ok(taskInputSourceService.partialUpdateTaskInputSource(id, updates));
    }

    @DeleteMapping("/{id}/deleteTaskInputSourceById")
    public ResponseEntity<Void> deleteTaskInputSource(@PathVariable UUID id) {
        taskInputSourceService.deleteTaskInputSource(id);
        return ResponseEntity.noContent().build();
    }
}

