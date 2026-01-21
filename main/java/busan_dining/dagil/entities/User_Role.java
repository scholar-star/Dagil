package busan_dining.dagil.entities;

import jakarta.persistence.*;

@Entity
public class User_Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_role_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user_id;
}
