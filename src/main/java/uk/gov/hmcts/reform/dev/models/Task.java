package uk.gov.hmcts.reform.dev.models;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NonNull;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private @NonNull String title;
    private @NonNull String description;
    private @NonNull String status;
    private @NonNull LocalDateTime dueDateTime;

    public TaskDTO toDTO () {
        return (
            new TaskDTO (
                this.getTitle(),
                this.getDescription(),
                this.getStatus(),
                this.getDueDateTime()
            )
        );
    }
}
