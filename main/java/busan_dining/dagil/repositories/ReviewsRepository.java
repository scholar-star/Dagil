package busan_dining.dagil.repositories;

import busan_dining.dagil.dto.LandmarkDTO;
import busan_dining.dagil.entities.Posts;
import busan_dining.dagil.entities.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewsRepository extends JpaRepository<Reviews, Long> {
    List<Reviews> findByPosts(Posts posts);
}
