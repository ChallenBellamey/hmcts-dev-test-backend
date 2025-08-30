package uk.gov.hmcts.reform.dev.models;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private TaskRepository taskRepository;
    
    public TaskService(
        TaskRepository taskRepository
    ) {
        this.taskRepository = taskRepository;
    }

    public Optional<Task> getTaskById(Integer id) {
        return taskRepository.findById(id);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public void addTask(Task task) {
        taskRepository.save(task);
    }
}
