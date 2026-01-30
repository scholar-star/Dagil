package busan_dining.dagil.dto;

public record SignupDTO (
        String nickname,
        String email,
        String loginID,
        String password,
        boolean traveler
) {}
