package com.project.taskmanagement.ServiceImpl;


import com.project.taskmanagement.CustomException.ResourceNotFoundException;
import com.project.taskmanagement.DTO.TaskDTO;
import com.project.taskmanagement.ENUM.Priority;
import com.project.taskmanagement.ENUM.TaskStatus;
import com.project.taskmanagement.Entity.Task;
import com.project.taskmanagement.Entity.TaskInputSource;
import com.project.taskmanagement.Entity.User;
import com.project.taskmanagement.Repository.TaskRepository;
import com.project.taskmanagement.Repository.UserRepository;
import com.project.taskmanagement.Service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TaskDTO createTask(TaskDTO taskDTO) {
        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setPriority(taskDTO.getPriority());
        task.setDeadline(taskDTO.getDeadline());
        task.setStatus(taskDTO.getStatus());
        task.setProgressNotes(taskDTO.getProgressNotes());
        task.setCreatedAt(taskDTO.getCreatedAt());
        task.setCompletionDate(taskDTO.getCompletionDate());
        task.setAdditionalNotes(taskDTO.getAdditionalNotes());

        // Assigning Creator and Assignee
        User creator = userRepository.findById(taskDTO.getCreatedBy())
                .orElseThrow(() -> new RuntimeException("Creator not found with id: " + taskDTO.getCreatedBy()));
        task.setCreatedBy(creator);

        if (taskDTO.getAssignee() != null) {
            User assignee = userRepository.findById(taskDTO.getAssignee())
                    .orElseThrow(() -> new RuntimeException("Assignee not found with id: " + taskDTO.getAssignee()));
            task.setAssignee(assignee);
        }

        Task savedTask = taskRepository.save(task);
        return mapToDTO(savedTask);
    }

    @Override
    public TaskDTO getTaskById(UUID id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
        return mapToDTO(task);
    }

    @Override
    public List<TaskDTO> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> getTasksByAssignee(UUID assigneeId) {
        List<Task> tasks = taskRepository.findByAssigneeId(assigneeId);
        return tasks.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public TaskDTO updateTask(UUID id, TaskDTO taskDTO) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));

        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setPriority(taskDTO.getPriority());
        task.setDeadline(taskDTO.getDeadline());
        task.setStatus(taskDTO.getStatus());
        task.setProgressNotes(taskDTO.getProgressNotes());
        task.setCompletionDate(taskDTO.getCompletionDate());
        task.setAdditionalNotes(taskDTO.getAdditionalNotes());

        if (taskDTO.getAssignee() != null) {
            User assignee = userRepository.findById(taskDTO.getAssignee())
                    .orElseThrow(() -> new RuntimeException("Assignee not found with id: " + taskDTO.getAssignee()));
            task.setAssignee(assignee);
        }

        Task updatedTask = taskRepository.save(task);
        return mapToDTO(updatedTask);
    }

    @Override
    public void deleteTask(UUID id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
        taskRepository.delete(task);
    }

    private TaskDTO mapToDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setTitle(task.getTitle());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setPriority(task.getPriority());
        taskDTO.setDeadline(task.getDeadline());
        taskDTO.setStatus(task.getStatus());
        taskDTO.setProgressNotes(task.getProgressNotes());
        taskDTO.setCreatedAt(task.getCreatedAt());
        taskDTO.setCompletionDate(task.getCompletionDate());
        taskDTO.setAdditionalNotes(task.getAdditionalNotes());

        if (task.getCreatedBy() != null) {
            taskDTO.setCreatedBy(task.getCreatedBy().getId());
        }
        if (task.getAssignee() != null) {
            taskDTO.setAssignee(task.getAssignee().getId());
        }

        return taskDTO;
    }

    @Override
    public TaskDTO partialUpdateTask(UUID id, Map<String, Object> updates) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));

        // Apply only the fields that are present in the updates map
        if (updates.containsKey("title")) {
            task.setTitle((String) updates.get("title"));
        }
        if (updates.containsKey("description")) {
            task.setDescription((String) updates.get("description"));
        }
        if (updates.containsKey("priority")) {
            task.setPriority(Priority.valueOf((String) updates.get("priority")));
        }
        if (updates.containsKey("deadline")) {
            task.setDeadline((String) updates.get("deadline"));
        }
        if (updates.containsKey("status")) {
            task.setStatus(TaskStatus.valueOf((String) updates.get("status")));
        }
        if (updates.containsKey("progressNotes")) {
            task.setProgressNotes((String) updates.get("progressNotes"));
        }
        if (updates.containsKey("assignee")) {
            task.setAssignee((User) updates.get("assignee"));
        }
        if (updates.containsKey("completionDate")) {
            String dateStr = (String) updates.get("completionDate");
            task.setCompletionDate(LocalDate.parse(dateStr));
        }
        if (updates.containsKey("additionalNotes")) {
            task.setAdditionalNotes((String) updates.get("additionalNotes"));
        }

        // Note: createdBy and createdAt should not be modifiable via PATCH

        Task savedTask = taskRepository.save(task);

        return modelMapper.map(savedTask, TaskDTO.class);
    }


}

