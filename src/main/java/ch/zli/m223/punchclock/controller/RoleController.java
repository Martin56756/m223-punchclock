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

    /**
     * Gets all roles from the database
     * @return A list containing all roles
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Role> getAllRoles() {
        return roleService.findAll();
    }

    /**
     * Gets a role by its name
     * @param name The name of the role
     * @return The matching role
     */
    @GetMapping("/{roleName}")
    @ResponseStatus(HttpStatus.OK)
    public Role getRoleByName(@PathVariable("roleName") String name) {
        return roleService.getRoleByName(name);
    }

    /**
     * Creates a role in the database
     * @param role The role to save to the database
     * @return The newly created role
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Role createRole(@Valid @RequestBody Role role) {
        return roleService.createRole(role);
    }

    /**
     * Deletes the specified role from the database
     * @param roleId The ID of the role to delete
     */
    @DeleteMapping("/{roleId]")
    @ResponseStatus(HttpStatus.OK)
    public void deleteRole(@PathVariable("roleId") long roleId) {
        roleService.deleteRole(roleId);
    }

    /**
     * Updates the specified role in the database
     * @param role The role to update
     * @return The updated role
     */
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Role updateRole(@Valid @RequestBody Role role) {
        return roleService.updateRole(role);
    }
}
