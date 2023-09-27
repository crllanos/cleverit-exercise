package cl.cleverit.exercise.repository;

import cl.cleverit.exercise.entity.TaskEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TaskRepository extends CrudRepository<TaskEntity, UUID> {


}