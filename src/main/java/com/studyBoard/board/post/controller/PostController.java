package com.studyBoard.board.post.controller;

import com.studyBoard.board.board.service.BoardService;
import com.studyBoard.board.comment.service.CommentService;
import com.studyBoard.board.post.PostService;
import com.studyBoard.board.post.domain.Post;
import com.studyBoard.board.post.domain.PostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
public class PostController {
    private final PostService postService;
    private final BoardService boardService;
    private final CommentService commentService;


    @Autowired
    public PostController(PostService postService, BoardService boardService, CommentService commentService) {
        this.postService = postService;
        this.boardService = boardService;
        this.commentService = commentService;
    }


    @GetMapping("/posts/create")
    public String CreatePostForm(@RequestParam("boardId") Long boardId, Model model) {
        model.addAttribute("boardId", boardId); // 모델에 boardId 추가
        model.addAttribute("postDTO", new PostDTO()); //  PostDTO 객체 추가
        return "post/createPost";
    }

    // 게시물 생성하기
    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute("postDTO") PostDTO postDTO, @RequestParam("boardId") Long boardId) {
        postService.createPostWithBoardId1(postDTO, boardId);
        return "redirect:/boards/" + boardId;
    }

    @GetMapping("/posts/{postId}")
    public String getPostDetail(@PathVariable Long postId, Model model) {
        Post post = postService.getPostById(postId);
        model.addAttribute("post", post);
        model.addAttribute("comments", commentService.getCommentsByPostId(postId));
        return "post/post";
    }


    // 게시판 수정 기능
    @GetMapping("posts/{postId}/edit")
    public String showEditForm(@PathVariable Long postId, Model model) {
        Post post = postService.getPostById(postId);
        model.addAttribute("post", post);

        return "post/editPost";
    }



    @PostMapping("/posts/{postId}/edit")
    public String EditForm(@PathVariable Long postId, @ModelAttribute("post") PostDTO postDTO) {
        postDTO.setId(postId);
        postService.updatePost(postDTO);
        return "redirect:/posts/" + postId;
    }


    @DeleteMapping("/posts/{postId}")
    public String deletePost(@PathVariable Long postId) {
        Long boardId = postService.getPostBoardId(postId);
        postService.deletePost(postId);
        return "redirect:/boards/" + boardId;
    }









}
