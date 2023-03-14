package com.saveyourtask.tasktracker.service;

import com.saveyourtask.tasktracker.data.dto.TaskDto;
import com.saveyourtask.tasktracker.data.dto.request.TaskRequest;
import com.saveyourtask.tasktracker.data.model.Task;
import com.saveyourtask.tasktracker.data.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public TaskDto addTask(TaskRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Task task = Task.builder()
                .name(request.getName())
                .description(request.getDescription())
                .dueDateTime(LocalDate.parse(request.getDueDateTime(), formatter))
                .build();
        Task saved = taskRepository.save(task);
        return getTaskDto(saved);
    }

    private TaskDto getTaskDto(Task task) {
        return TaskDto.builder()
                .code(HttpStatus.OK.value())
                .message("Task added successfully")
                .id(task.getId())
                .isSuccess(true).build();
    }

    public Task updateTask(Long id, TaskRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Task existingTask = taskRepository.findById(id).orElse(null);
        if (existingTask == null) {
            return null;
        }
        existingTask.setName(request.getName());
        existingTask.setDescription(request.getDescription());
        existingTask.setDueDateTime(LocalDate.parse(request.getDueDateTime(), formatter));
        return taskRepository.save(existingTask);
    }

    public void deleteTask(Long id) {
        taskRepository.findById(id).ifPresent(task -> taskRepository.delete(task));
    }
}
