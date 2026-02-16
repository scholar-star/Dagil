package busan_dining.dagil.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Landmarks {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Float latitude;

    private Float longitude;
}
