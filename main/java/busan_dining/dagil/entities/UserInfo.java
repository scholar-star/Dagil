package busan_dining.dagil.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long info_id;

    @OneToOne
    @JoinColumn(name="user_id")
    private Users user_id;

    private Boolean foreigner;

    @Column(nullable = false)
    private String email;

    private String imageURL;

    @Column(nullable = false)
    private String nickname;
}
