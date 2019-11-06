package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.Role;
import ch.zli.m223.punchclock.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role createRole(Role role) {
        return roleRepository.saveAndFlush(role);
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public void deleteRole(long roleId) {
        roleRepository.deleteById(roleId);
    }

    public Role updateRole(Role role) {
        return roleRepository.saveAndFlush(role);
    }
}
