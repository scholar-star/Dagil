package busan_dining.dagil.entities;

import jakarta.persistence.*;

@Entity
public class Restaurants {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Float latitude;

    private Float longitude;

    @ManyToOne
    @JoinColumn(name = "landmarks_id")
    private Landmarks landmark;
}
