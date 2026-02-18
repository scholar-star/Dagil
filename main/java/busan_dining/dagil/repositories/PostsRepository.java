package busan_dining.dagil.repositories;

import busan_dining.dagil.entities.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    public Posts findByID(Long id);
}
