package busan_dining.dagil.entities;

import jakarta.persistence.*;

public class PostsReview {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "posts_id")
    private Posts posts;

    private String review;

    private Integer stars;
}
