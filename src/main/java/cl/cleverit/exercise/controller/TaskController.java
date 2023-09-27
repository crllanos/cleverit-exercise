package cl.cleverit.exercise.controller;

import cl.cleverit.exercise.dto.TaskDTO;
import cl.cleverit.exercise.entity.TaskEntity;
import cl.cleverit.exercise.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/{id}")
    public TaskEntity getTask(@PathVariable UUID id){
        log.info(String.format("[GET] /%s", id));
        TaskEntity t = taskService.readTask(id);
        log.info(String.format("response: %s", t));
        return t;
    }

    @GetMapping
    public List<TaskEntity> getTaskList(){
        log.info("[GET] / ");
        List<TaskEntity> list = taskService.readTaskList();
        log.info(String.format("response: %s", list));
        return list;
    }

    @PostMapping
    public TaskEntity createTask(@RequestBody TaskDTO task){
        log.info(String.format("[POST] / : %s", task));
        TaskEntity t = taskService.createTask(TaskEntity.builder()
                        .title(task.getTitle())
                        .description(task.getDescription())
                        .dueDate(task.getDueDate())
                .build());
        log.info(String.format("response: %s", t));
        return t;
    }

    @PutMapping("/{id}")
    public TaskEntity editTask(@PathVariable UUID id, @RequestBody TaskDTO task){
        log.info(String.format("[PUT] /%s : %s", id, task));
        TaskEntity t = taskService.updateTask(id, TaskEntity.builder()
                    .title(task.getTitle())
                    .description(task.getDescription())
                    .dueDate(task.getDueDate())
                .build());
        log.info(String.format("response: %s", t));
        return t;
    }


    @PatchMapping("/{id}")
    public TaskEntity editTaskStatus(@PathVariable UUID id, @RequestBody TaskDTO task){
        log.info(String.format("[PUT] /%s : %s", id, task));
        TaskEntity t = taskService.updateTaskStatus(id, TaskEntity.builder()
                .status(task.getStatus())
                .build());
        log.info(String.format("response: %s", t));
        return t;
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable UUID id){
        log.info(String.format("[DELETE] /%s", id));
        taskService.deleteTask(id);
        return ResponseEntity.ok("deleted");
    }
}
