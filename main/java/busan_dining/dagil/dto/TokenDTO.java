package busan_dining.dagil.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenDTO {
    String accessToken;
    String refreshToken;
}
