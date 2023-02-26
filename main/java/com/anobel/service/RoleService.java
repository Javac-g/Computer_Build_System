package com.anobel.service;

import com.anobel.model.Role;

import java.util.List;

public interface RoleService {
    Role create(Role role);
    Role find(long id);
    Role update(long id,Role role);
    void delete(long id);
    List<Role> getAll();
}
