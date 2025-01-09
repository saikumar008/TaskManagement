package com.project.taskmanagement.Repository;

import com.project.taskmanagement.Entity.TaskInputSource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskInputSourceRepository extends JpaRepository<TaskInputSource, UUID> {
}

