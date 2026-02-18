package busan_dining.dagil.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users user;

    @OneToOne
    @JoinColumn(name = "restaurants_id")
    private Restaurants restaurant;

    private String description;

    public Restaurants getRestaurant() {
        return restaurant;
    }
}
