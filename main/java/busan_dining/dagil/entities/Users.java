package busan_dining.dagil.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    public String loginID;

    @Column(nullable = false)
    public String password;

    @OneToMany(cascade = CascadeType.REMOVE)
    List<UserRole> user_roles = new ArrayList<UserRole>();
}
