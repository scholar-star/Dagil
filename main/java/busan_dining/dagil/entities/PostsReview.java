package busan_dining.dagil.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostsReview {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne // 1개의 Post에 다수 리뷰
    @JoinColumn(name = "posts_id")
    private Posts posts;

    @OneToMany
    private List<Reviews> reviews;
}
