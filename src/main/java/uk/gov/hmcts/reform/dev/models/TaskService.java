package uk.gov.hmcts.reform.dev.models;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private TaskRepository taskRepository;
    
    public TaskService(
        TaskRepository taskRepository
    ) {
        this.taskRepository = taskRepository;
    }

    public TaskDTO getTaskById(Integer id) {
        Task task = taskRepository.findById(id)
            .orElseThrow(() -> new IllegalStateException(id + " not found."));

        return task.toDTO();
    }

    public List<TaskDTO> getAllTasks() {
        return taskRepository
            .findAll()
            .stream()
            .map(task -> task.toDTO())
            .collect(Collectors.toList());
    }

    public void addTask(TaskDTO taskDTO) {
        taskRepository.save(taskDTO.toTask());
    }

    public void deleteTask(Integer id) {
        taskRepository.deleteById(id);
    }
}
