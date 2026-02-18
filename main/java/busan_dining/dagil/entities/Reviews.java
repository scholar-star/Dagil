package busan_dining.dagil.entities;

import jakarta.persistence.*;

@Entity
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Posts post;

    @ManyToOne
    private Users user;

    private String review;

    private Integer stars;

    public Integer getStars() {
        return stars;
    }

    public String getReview() {
        return review;
    }
}
