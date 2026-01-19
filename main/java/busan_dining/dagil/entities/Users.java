package busan_dining.dagil.entities;

import jakarta.persistence.*;
import org.springframework.context.annotation.Primary;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    public String loginID;

    @Column(nullable = false)
    public String password;
}
