package com.studyBoard.board.post.controller;

import com.studyBoard.board.post.PostService;
import com.studyBoard.board.post.domain.Post;
import com.studyBoard.board.post.domain.PostDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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
