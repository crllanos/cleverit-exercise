package cl.cleverit.exercise.service.impl;

import cl.cleverit.exercise.entity.RoleEntity;
import cl.cleverit.exercise.repository.RoleRepository;
import cl.cleverit.exercise.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public RoleEntity saveRole(RoleEntity role) {
        return roleRepository.save(role);
    }

    @Override
    public RoleEntity findByRolename(String rolename) {
        return roleRepository.findByRolename(rolename);
    }
}