package cl.cleverit.exercise.service.impl;

import cl.cleverit.exercise.entity.TaskEntity;
import cl.cleverit.exercise.enums.TaskStatusEnum;
import cl.cleverit.exercise.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    @Override
    public TaskEntity createTask(TaskEntity task) {
        task.setId(UUID.randomUUID());
        task.setStatus(TaskStatusEnum.PROGRESS);
        return null;
    }

    @Override
    public TaskEntity readTask(UUID id) {
        return null;
    }

    @Override
    public List<TaskEntity> readTaskList() {
        return null;
    }

    @Override
    public TaskEntity updateTask(UUID id, TaskEntity task) {
        return null;
    }

    @Override
    public void deleteTask(UUID id) {

    }
}
