package busan_dining.dagil.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String loginID;

    @Column(nullable = false)
    private String password;

    @OneToMany(cascade = CascadeType.REMOVE)
    List<UserRole> user_roles = new ArrayList<UserRole>();
}
