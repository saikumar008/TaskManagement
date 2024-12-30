package com.project.taskmanagement.Repository;

import com.project.taskmanagement.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
    List<Task> findByAssigneeId(UUID assigneeId); // Get tasks assigned to a specific user
}

