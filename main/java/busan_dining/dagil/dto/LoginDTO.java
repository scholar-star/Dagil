package busan_dining.dagil.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
    @NotBlank String loginID,
    @NotBlank String password
) {}
