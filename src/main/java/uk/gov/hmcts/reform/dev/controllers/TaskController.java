package uk.gov.hmcts.reform.dev.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;

import static org.springframework.http.ResponseEntity.ok;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;

import uk.gov.hmcts.reform.dev.models.TaskDTO;
import uk.gov.hmcts.reform.dev.models.TaskService;
import uk.gov.hmcts.reform.dev.exception.NotFoundException;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController (TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping(value = "{id}", produces = "application/json")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable int id) throws NotFoundException {
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
    public void addTask(@RequestBody @Valid TaskDTO taskDTO) {
        taskService.addTask(taskDTO);
    }
    
    @DeleteMapping(value = "{id}")
    public void deleteTask(@PathVariable int id) throws NotFoundException {
        taskService.deleteTask(id);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> exceptionHandler(NotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException exception) {
        Map<String, String> errorMap = new HashMap<>();

        exception.getBindingResult().getAllErrors().forEach(error -> {
            String errorName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errorMap.put(errorName, errorMessage);
        });

        return errorMap;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
