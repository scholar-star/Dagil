package busan_dining.dagil.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_role_id;

    @ManyToOne // UserInfo에서 여러 사용자 사용 가능
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne // UserInfo에서 여러 Role 사용 가능
    @JoinColumn(name = "role_id")
    private Role role;

    public Role getRole() {
        return role;
    }
}
