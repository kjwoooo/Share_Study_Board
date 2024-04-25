package com.studyBoard.board.post.controller;

import com.studyBoard.board.post.PostService;
import com.studyBoard.board.post.domain.PostDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostController {
    private final PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/post/write")
    public String postmortem() {
        return "createPost";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute PostDTO postDTO) {
        postService.createPost(postDTO);
        return "redirect:/";
    }



}
