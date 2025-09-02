package uk.gov.hmcts.reform.dev.models;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private TaskRepository taskRepository;
    
    public TaskService(
        TaskRepository taskRepository
    ) {
        this.taskRepository = taskRepository;
    }

    public Task getTaskById(Integer id) {
        return taskRepository.findById(id)
            .orElseThrow(() -> new IllegalStateException(id + " not found."));
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public void addTask(Task task) {
        taskRepository.save(task);
    }

    public void deleteTask(Integer id) {
        taskRepository.deleteById(id);
    }
}
