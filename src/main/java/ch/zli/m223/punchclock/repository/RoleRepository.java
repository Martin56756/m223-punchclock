package ch.zli.m223.punchclock.repository;

import ch.zli.m223.punchclock.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("SELECT r FROM Role AS r WHERE r.name = ?1")
    Role findByName(String name);
}
