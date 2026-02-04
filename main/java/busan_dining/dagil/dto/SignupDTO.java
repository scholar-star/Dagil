package busan_dining.dagil.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record SignupDTO (
        @NotBlank String nickname,

        @NotBlank
        @Email // 비밀번호 찾기용 이메일
        String email,

        @NotBlank String loginID,
        @NotBlank String password,
        boolean traveler
) {}
