package cl.cleverit.exercise.service;

import cl.cleverit.exercise.entity.RoleEntity;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {

    RoleEntity saveRole(RoleEntity role);

    RoleEntity findByRolename(String rolename);

}