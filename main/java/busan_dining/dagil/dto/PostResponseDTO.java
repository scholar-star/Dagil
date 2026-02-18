package busan_dining.dagil.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record PostResponseDTO (
    String restaurantName,
    String image, // 식당 이미지
    List<String> reviews, // 리뷰들
    Float stars, // 평균 별점
    String description, // 처음 post를 남길 때 설명
    List<String> menus, // 많이 시킨 메뉴
    Integer price, // 주 가격
    List<LandmarkDTO> landmarks
) {}
