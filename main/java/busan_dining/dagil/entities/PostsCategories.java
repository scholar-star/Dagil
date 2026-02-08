package busan_dining.dagil.entities;

import jakarta.persistence.*;

public class PostsCategories {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "posts_id")
    private Posts posts;

    @ManyToOne
    @JoinColumn(name = "categories_id")
    private Categories categories;
}
