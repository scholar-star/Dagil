package busan_dining.dagil.entities;

import jakarta.persistence.*;

@Entity
public class Menus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "posts_id")
    private Posts posts;

    private String menu;

    private int price;
}
