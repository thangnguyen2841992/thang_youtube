package com.thang.youtube.service.role;

import com.thang.youtube.model.entity.Role;
import com.thang.youtube.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRoleRepository roleRepository;
    @Override
    public Iterable<Role> getAll() {
        return this.roleRepository.findAll();
    }

    @Override
    public Optional<Role> getById(Long id) {
        return this.roleRepository.findById(id);
    }

    @Override
    public Role save(Role role) {
        return this.roleRepository.save(role);
    }

    @Override
    public void deleteById(Long id) {
        this.roleRepository.deleteById(id);
    }
}
