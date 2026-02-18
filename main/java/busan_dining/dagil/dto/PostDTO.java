package busan_dining.dagil.dto;


import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record PostDTO (
        @NotBlank Long userId,
        @NotBlank Long restaurantId,
        List<Long> categories,
        Integer stars,
        List<String> menus,
        String description,
        Integer price
) {}
