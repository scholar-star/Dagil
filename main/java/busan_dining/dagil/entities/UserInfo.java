package busan_dining.dagil.entities;

import jakarta.persistence.*;

@Entity
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long info_id;

    @Column(nullable = false)
    private Long user_id;

    public Boolean foreigner;

    @Column(nullable = false)
    public String email;

    public String imageURL;

    @Column(nullable = false)
    public String nickname;
}
