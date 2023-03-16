package com.saveyourtask.tasktracker.controller;

import com.saveyourtask.tasktracker.data.dto.TaskDto;
import com.saveyourtask.tasktracker.data.dto.request.TaskRequest;
import com.saveyourtask.tasktracker.data.model.Task;
import com.saveyourtask.tasktracker.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tasks")
public class TaskController {
    @Autowired
    private TaskService service;

    @PostMapping("/addtasks")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> addTasks(@RequestBody TaskRequest request){
        TaskDto taskDto = service.addTask(request);
        return ResponseEntity.ok().body(taskDto);
    }

    @PutMapping("/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody TaskRequest request){
        Task updatedTask = service.updateTask(id, request);
        if (updatedTask == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedTask);
    }
    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        service.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> getReminderById(@PathVariable Long id) {
        Task task= service.getTaskById(id);
        if (task == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(task);
    }

    @GetMapping
    @CrossOrigin(origins = "*")
    public List<Task> getAllTasks(){
        return service.getAllTasks();
    }
}
