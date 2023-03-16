package com.saveyourtask.tasktracker.data.repository;

import com.saveyourtask.tasktracker.data.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
