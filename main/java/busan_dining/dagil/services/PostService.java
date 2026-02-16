package busan_dining.dagil.services;

import busan_dining.dagil.dto.PostDTO;
import busan_dining.dagil.entities.Posts;
import busan_dining.dagil.entities.PostsReview;
import busan_dining.dagil.entities.Restaurants;
import busan_dining.dagil.entities.Users;
import busan_dining.dagil.repositories.PostsRepository;
import busan_dining.dagil.repositories.PostsReviewRepository;
import busan_dining.dagil.repositories.RestaurantsRepository;
import busan_dining.dagil.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostsRepository postsRepository;
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
                .build();

        postsRepository.save(newPost);

        PostsReview review = PostsReview.builder()
                .posts(newPost)
                .review(postDTO.review())
                .stars(postDTO.stars())
                .build();

        postsReviewRepository.save(review);
        return "등록이 완료되었습니다";
    }
}
