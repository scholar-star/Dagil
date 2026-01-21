package busan_dining.dagil.repositories;

import busan_dining.dagil.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    public Users findByLoginID(String loginID);
}
