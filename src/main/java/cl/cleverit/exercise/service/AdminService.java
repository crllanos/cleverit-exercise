package cl.cleverit.exercise.service;

import cl.cleverit.exercise.entity.AdminEntity;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {

    AdminEntity saveAdmin(AdminEntity admin);

    AdminEntity findAdmin(String username);

    void addRoleToAdmin(String username, String rolename);
}