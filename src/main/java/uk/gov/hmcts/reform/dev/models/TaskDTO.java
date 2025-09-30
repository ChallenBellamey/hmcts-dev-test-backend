package uk.gov.hmcts.reform.dev.models;

import java.time.LocalDateTime;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Future;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaskDTO {

    @Valid

    public int id;
    
    @NotBlank(message = "Title is required.")
    @NotNull(message = "Title is required.")
    @Size(min = 1, max = 100, message = "Title is required to be between 1 and 100 characters.")
    public String title;

    @Size(max = 100, message = "Description is required to no more than 100 characters.")
    public String description;

    @NotBlank(message = "Status is required.")
    @NotNull(message = "Status is required.")
    @Size(min = 1, max = 10, message = "Status is required to be between 1 and 10 characters.")
    public String status;

    @NotNull(message = "Due date is required.")
    @Future(message = "Due date must not have passed.")
    public LocalDateTime dueDateTime;

    public Task toTask() {
        return new Task(
            this.getId(),
            this.getTitle(),
            this.getDescription(),
            this.getStatus(),
            this.getDueDateTime()
        );
    }
}
