package busan_dining.dagil.repositories;

import busan_dining.dagil.entities.PostsReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostsReviewRepository extends JpaRepository<PostsReview, Long> {
    public List<PostsReview> findByPostId(Long postId);
}
