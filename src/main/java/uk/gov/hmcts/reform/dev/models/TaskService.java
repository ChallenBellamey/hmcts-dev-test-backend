package uk.gov.hmcts.reform.dev.models;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import uk.gov.hmcts.reform.dev.exception.NotFoundException;

@Service
public class TaskService {

    private TaskRepository taskRepository;
    
    public TaskService(
        TaskRepository taskRepository
    ) {
        this.taskRepository = taskRepository;
    }

    public TaskDTO getTaskById(Integer id) throws NotFoundException {
        Task task = taskRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Error: Task " + id + " not found."));

        return task.toDTO();
    }

    public List<TaskDTO> getAllTasks() {
        return taskRepository
            .findAll()
            .stream()
            .map(task -> task.toDTO())
            .collect(Collectors.toList());
    }

    public Task addTask(TaskDTO taskDTO) {
        return taskRepository.save(taskDTO.toTask());
    }

    public Task updateTask(TaskDTO taskDTO) {
        return taskRepository.save(taskDTO.toTask());
    }

    public void deleteTask(Integer id) throws NotFoundException {
        if (!taskRepository.existsById(id)) {
            throw new NotFoundException("Error: Task " + id + " not found.");
        }
        
        taskRepository.deleteById(id);
    }
}
