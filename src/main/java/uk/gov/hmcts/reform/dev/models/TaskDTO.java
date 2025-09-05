package uk.gov.hmcts.reform.dev.models;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaskDTO {
    
    private String title;
    private String description;
    private String status;
    private LocalDateTime dueDateTime;

    public Task toTask () {
        return (
            new Task (
                this.getTitle(),
                this.getDescription(),
                this.getStatus(),
                this.getDueDateTime()
            )
        );
    }
}
