package cl.cleverit.exercise.service;

import cl.cleverit.exercise.entity.TaskEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface TaskService {
    TaskEntity createTask(TaskEntity task);
    TaskEntity readTask(UUID id);
    List<TaskEntity> readTaskList();
    TaskEntity updateTask(UUID id, TaskEntity task);
    void deleteTask(UUID id);
}
