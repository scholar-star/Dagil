package busan_dining.dagil.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_role_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user_id;

    private List<Role> role;

    public List<Role> getRoles() {
        return role;
    }
}
