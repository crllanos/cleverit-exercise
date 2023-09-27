package cl.cleverit.exercise.repository;

import cl.cleverit.exercise.entity.AdminEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends CrudRepository<AdminEntity, Long> {

    AdminEntity findByUsername(String username);

}