package cl.cleverit.exercise.service.impl;

import cl.cleverit.exercise.entity.TaskEntity;
import cl.cleverit.exercise.enums.TaskStatusEnum;
import cl.cleverit.exercise.repository.TaskRepository;
import cl.cleverit.exercise.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public TaskEntity createTask(TaskEntity task) {
        task.setId(UUID.randomUUID());
        task.setStatus(TaskStatusEnum.PROGRESS.getName());
        // return task;
        return taskRepository.save(task);
    }

    @Override
    public TaskEntity readTask(UUID id) {
        return null;
    }

    @Override
    public List<TaskEntity> readTaskList() {
        return StreamSupport.stream(taskRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public TaskEntity updateTask(UUID id, TaskEntity task) {
        return null;
    }

    @Override
    public void deleteTask(UUID id) {

    }
}
