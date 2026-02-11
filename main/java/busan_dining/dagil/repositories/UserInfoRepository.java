package busan_dining.dagil.repositories;

import busan_dining.dagil.entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    public UserInfo findByNickname(String nickname);
}