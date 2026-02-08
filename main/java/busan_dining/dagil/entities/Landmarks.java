package busan_dining.dagil.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Landmarks {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Float latitude;

    private Float longitude;
}
