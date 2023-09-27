package cl.cleverit.exercise.controller;

import cl.cleverit.exercise.Util;
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

    private final Util util;

    @GetMapping("/{id}")
    public TaskEntity getTask(@PathVariable UUID id){
        log.info(String.format("[GET] /%s", id));
        TaskEntity t = taskService.readTask(id);
        log.info(String.format("response: %s", util.obj2Json(t)));
        return t;
    }

    @GetMapping
    public List<TaskEntity> getTaskList(){
        log.info("[GET]");
        List<TaskEntity>  list = taskService.readTaskList();
        log.info(String.format("response: %s", util.obj2Json(list)));
        return list;
    }

    @PostMapping
    public TaskEntity createTask(@RequestBody TaskDTO task){
        log.info(String.format("[POST] /%s", util.obj2Json(task)));
        TaskEntity t = taskService.createTask(TaskEntity.builder()
                        .title(task.getTitle())
                        .description(task.getDescription())
                        .dueDate(task.getDueDate())
                .build());
        log.info(String.format("response: %s", util.obj2Json(t)));
        return t;
    }

    @PutMapping("/{id}")
    public TaskEntity editTask(@PathVariable UUID id, @RequestBody TaskEntity task){
        log.info(String.format("[PUT] /%s : %s", id, util.obj2Json(task)));
        TaskEntity t = taskService.updateTask(id, task);
        log.info(String.format("response: %s", util.obj2Json(t)));
        return t;
    }

    // @PatchMapping("/{id}")
    // todo completar un task

    @DeleteMapping("/{id}")
    public ResponseEntity.BodyBuilder deleteTask(@PathVariable UUID id){
        log.info(String.format("[DELETE] /%s", id));
        taskService.deleteTask(id);
        return ResponseEntity.ok();
    }
}
