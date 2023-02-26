package com.anobel.service.impl;

import com.anobel.exception.NullEntityReferenceException;
import com.anobel.model.Role;
import com.anobel.repository.RoleRepository;
import com.anobel.service.RoleService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role create(Role role) {
        if (role != null){
            return roleRepository.save(role);
        }
        throw new EntityNotFoundException("Role cant be null - create");
    }

    @Override
    public Role find(long id) {
        return roleRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Role with id " + id + " not found"));

    }

    @Override
    public Role update(long id,Role role) {
        Role x = find(id);
        if (x != null){
            x.setId(role.getId());
            x.setName(role.getName());
            return roleRepository.save(x);
        }
        throw new NullEntityReferenceException("Role cant be null - update");
    }

    @Override
    public void delete(long id) {
        roleRepository.delete(find(id));
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }
}
