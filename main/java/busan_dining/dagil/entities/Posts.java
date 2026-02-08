package busan_dining.dagil.entities;

import jakarta.persistence.*;

@Entity
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "restaurants_id")
    private Restaurants restaurant;
}
