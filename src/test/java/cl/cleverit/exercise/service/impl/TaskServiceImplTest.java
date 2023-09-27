package cl.cleverit.exercise.service.impl;

import cl.cleverit.exercise.entity.TaskEntity;
import cl.cleverit.exercise.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class TaskServiceImplTest {

    @InjectMocks
    private TaskServiceImpl taskServiceImpl;

    @Mock
    private TaskRepository taskRepository;

    private static String mockUUID = "89cd74bb-b1d3-4248-b1fd-64aba2d6c67b";

    @Test
    public void shouldNotSaveTask_emptyTitle(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            taskServiceImpl.createTask(mockTask_emptyTitle());
        });
        String expectedMessage = "Must provide a valid task";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldNotUpdateTaskStatus_invalidStatus(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            taskServiceImpl.updateTaskStatus(UUID.fromString(mockUUID),
                    mockTask_invalidStatus());
        });
        String expectedMessage = "Must provide a valid task status";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldNotFindTask_notFound(){
        when(taskRepository.findById(any()))
                .thenThrow(new EntityNotFoundException(String.format("ID %s not found", mockUUID)));

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            taskServiceImpl.readTask(UUID.fromString(mockUUID));
        });
        String expectedMessage = "not found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldFindTask_Ok(){
        when(taskRepository.findById(any())).thenReturn(Optional.of(mockTask()));
        assertNotNull(taskServiceImpl.readTask(UUID.fromString(mockUUID)));
    }


    private TaskEntity mockTask() {
        return TaskEntity.builder()
                .id(UUID.fromString(mockUUID))
                .title("title")
                .description("description")
                .dueDate(LocalDate.now())
                .build();
    }

    private TaskEntity mockTask_emptyTitle() {
        TaskEntity t = mockTask();
        t.setTitle("");
        return t;
    }

    private TaskEntity mockTask_invalidStatus() {
        TaskEntity t = mockTask();
        t.setStatus("NOT_VALID");
        return t;
    }
}