package busan_dining.dagil.repositories;

import busan_dining.dagil.entities.UserRole;
import busan_dining.dagil.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    public UserRole findByUser(Users user);
}
