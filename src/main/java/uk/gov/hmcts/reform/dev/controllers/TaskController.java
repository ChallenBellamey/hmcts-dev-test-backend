package uk.gov.hmcts.reform.dev.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import static org.springframework.http.ResponseEntity.ok;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

import uk.gov.hmcts.reform.dev.models.TaskDTO;
import uk.gov.hmcts.reform.dev.models.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController (TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping(value = "{id}", produces = "application/json")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable int id) {
        return ok(
            taskService.getTaskById(id)
        );
    }

    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        return ok(
            taskService.getAllTasks()
        );
    }

    @PostMapping(value = "")
    public void addTask(@RequestBody TaskDTO taskDTO) {
        taskService.addTask(taskDTO);
    }
    
    @DeleteMapping(value = "{id}")
    public void deleteTask(@PathVariable int id) {
        taskService.deleteTask(id);
    }
}
