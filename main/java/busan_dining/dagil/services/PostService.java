package busan_dining.dagil.services;

import busan_dining.dagil.dto.PostDTO;
import busan_dining.dagil.dto.PostResponseDTO;
import busan_dining.dagil.entities.*;
import busan_dining.dagil.exception.NonePostException;
import busan_dining.dagil.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostsRepository postsRepository;
    private final ReviewsRepository reviewsRepository;
    private final RestaurantsRepository restaurantsRepository;
    private final UsersRepository usersRepository;
    private final PostsReviewRepository postsReviewRepository;

    // 다 작성한 Post DB에 올리기
    public String writePost(PostDTO postDTO) {
        Restaurants postRestaurant = restaurantsRepository.findByID(postDTO.restaurantId());
        Users postUser = usersRepository.findByid(postDTO.userId());

        Posts newPost = Posts.builder()
                .user(postUser)
                .restaurant(postRestaurant)
                .description(postDTO.description())
                .build();

        postsRepository.save(newPost);

        PostsReview review = PostsReview.builder()
                .posts(newPost)
                .build();

        postsReviewRepository.save(review);
        return "등록이 완료되었습니다";
    }

    public PostResponseDTO showPost(Long postId) throws NonePostException {
        Posts post = postsRepository.findById(postId).orElse(null);
        if (post != null) {
            List<Reviews> reviews = reviewsRepository.findByPosts(post);

            Integer totalStars = 0;
            for (Reviews review : reviews) {
                totalStars += review.getStars();
            }
            Float meanStars = (float) totalStars / reviews.size();

            List<String> rs = new ArrayList<>();
            for (Reviews pr : reviews) {
                rs.add(pr.getReview());
            }

            Restaurants postDining = post.getRestaurant();
            String restaurantName = postDining.getName();
            Landmarks landmarks = postDining.getLandmarks();

            PostResponseDTO response = PostResponseDTO.builder()
                    .restaurantName(restaurantName)
                    .reviews(rs)
                    .stars(meanStars)
                    .build();

            return response;
        } else {
            throw new NonePostException("존재하지 않는 Post입니다.");
        }
    }
}
