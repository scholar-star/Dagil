package busan_dining.dagil.controllers;

import busan_dining.dagil.dto.PostDTO;
import busan_dining.dagil.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/writePost")
    public ResponseEntity<String> writePost(@RequestBody PostDTO postDTO) {

    }
}
