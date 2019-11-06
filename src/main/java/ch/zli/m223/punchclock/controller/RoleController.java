package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.Role;
import ch.zli.m223.punchclock.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Role> getAllRoles() {
        return roleService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Role createRole(@Valid @RequestBody Role role) {
        return roleService.createRole(role);
    }

    @DeleteMapping("/{roleId]")
    @ResponseStatus(HttpStatus.OK)
    public void deleteRole(@PathVariable("roleId") long roleId) {
        roleService.deleteRole(roleId);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Role updateRole(@Valid @RequestBody Role role) {
        return roleService.updateRole(role);
    }
}
