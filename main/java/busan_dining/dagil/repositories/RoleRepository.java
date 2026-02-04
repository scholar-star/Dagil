package busan_dining.dagil.repositories;

import busan_dining.dagil.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    public Role findByid(Long id);
}
