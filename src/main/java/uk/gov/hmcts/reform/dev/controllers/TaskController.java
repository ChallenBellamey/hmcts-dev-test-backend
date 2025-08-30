package uk.gov.hmcts.reform.dev.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uk.gov.hmcts.reform.dev.models.Task;
import uk.gov.hmcts.reform.dev.models.TaskService;

import static org.springframework.http.ResponseEntity.ok;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController (TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping(value = "{id}", produces = "application/json")
    public ResponseEntity<Optional<Task>> getTaskById(@RequestParam int param) {
        return ok(
            taskService.getTaskById(param)
        );
    }

    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<List<Task>> getAllTasks() {
        return ok(
            taskService.getAllTasks()
        );
    }

    @PostMapping(value = "")
    public void addTask(@RequestBody Task task) {
        taskService.addTask(task);
    }
    
}
