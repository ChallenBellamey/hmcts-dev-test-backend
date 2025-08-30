package uk.gov.hmcts.reform.dev.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uk.gov.hmcts.reform.dev.models.Task;
import uk.gov.hmcts.reform.dev.models.TaskService;

import static org.springframework.http.ResponseEntity.ok;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("")
public class TaskController {

    private final TaskService taskService;

    public TaskController (TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping(value = "/tasks", produces = "application/json")
    public ResponseEntity<List<Task>> getAllTasks() {
        return ok(
            taskService.getAllTasks()
        );
    }

    @PostMapping(value = "/tasks")
    public void addTask(@RequestBody Task task) {
        taskService.addTask(task);
    }
    
}
