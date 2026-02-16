package busan_dining.dagil.dto;


import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record PostDTO (
        @NotBlank Long userId,
        @NotBlank Long restaurantId,
        List<Long> categories,
        String review,
        Integer stars,
        List<String> menus,
        Integer price
) {}
