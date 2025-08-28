package uk.gov.hmcts.reform.dev.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.hmcts.reform.dev.models.Task;

import java.time.LocalDateTime;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class TaskController {

    @GetMapping(value = "/task", produces = "application/json")
    public ResponseEntity<Task> getTask() {
        return ok(
            new Task(
                1,
                "Task Title",
                "Task Description",
                null,
                LocalDateTime.now()
        ));
    }
}
