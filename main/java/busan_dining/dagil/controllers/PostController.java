package busan_dining.dagil.controllers;

import busan_dining.dagil.dto.PostDTO;
import busan_dining.dagil.dto.PostResponseDTO;
import busan_dining.dagil.dto.RestaurantDTO;
import busan_dining.dagil.services.PostService;
import busan_dining.dagil.services.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final SearchService searchService;

    @PostMapping("/writePost")
    public ResponseEntity<String> writePost(@RequestBody PostDTO postDTO) {
        String message = postService.writePost(postDTO);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/restaurants")
    public ResponseEntity<List<RestaurantDTO>> getRestaurants(@RequestParam String keyword)
    throws IOException, InterruptedException {
        List<RestaurantDTO> restaurants = searchService.searchRestaurants(keyword);
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @GetMapping("/show/{postID}")
    public ResponseEntity<PostResponseDTO> showPost(@PathVariable long postID) {

    }
}
