package cl.cleverit.exercise.service.impl;

import cl.cleverit.exercise.entity.TaskEntity;
import cl.cleverit.exercise.enums.TaskStatusEnum;
import cl.cleverit.exercise.repository.TaskRepository;
import cl.cleverit.exercise.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public TaskEntity createTask(TaskEntity task) {
        validateTask(task);
        task.setId(UUID.randomUUID());
        task.setStatus(TaskStatusEnum.PROGRESS.getName());
        return taskRepository.save(task);
    }

    @Override
    public TaskEntity readTask(UUID id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("ID %s not found", id)));
    }

    @Override
    public List<TaskEntity> readTaskList() {
        return StreamSupport.stream(taskRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public TaskEntity updateTask(UUID id, TaskEntity task) {
        validateTask(task);
        TaskEntity t = readTask(id);
        t.setTitle(task.getTitle());
        t.setDescription(task.getDescription());
        t.setDueDate(task.getDueDate());
        return taskRepository.save(t);
    }

    @Override
    public TaskEntity updateTaskStatus(UUID id, TaskEntity task) {
        validateTaskStatus(task);
        TaskEntity t = readTask(id);
        t.setStatus(task.getStatus());
        return taskRepository.save(t);
    }

    @Override
    public void deleteTask(UUID id) {
        taskRepository.deleteById(id);
    }




    private static void validateTask(TaskEntity task) {
        if(StringUtils.isBlank(task.getTitle()) || StringUtils.isBlank(task.getDescription()) || Objects.isNull(task.getDueDate())){
            throw new IllegalArgumentException("Must provide a valid task!");
        }
    }

    private static void validateTaskStatus(TaskEntity task) {
        List<String> validStatus = new ArrayList<>();
        for(TaskStatusEnum s : TaskStatusEnum.values()){
            if(s.getName().equals(task.getStatus())){
                return;
            }
            validStatus.add(s.getName());
        }
        throw new IllegalArgumentException(
                String.format("Must provide a valid task status! (valid: %s)", StringUtils.join(validStatus,','))
        );
    }
}
