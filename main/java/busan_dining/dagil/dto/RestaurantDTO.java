package busan_dining.dagil.dto;

import jakarta.validation.constraints.NotBlank;

public record RestaurantDTO (
        @NotBlank String name,
        @NotBlank String address,
        String image
) {}
