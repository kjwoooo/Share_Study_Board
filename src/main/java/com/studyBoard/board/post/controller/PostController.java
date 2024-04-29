package com.studyBoard.board.post.controller;

import com.studyBoard.board.board.domain.Board;
import com.studyBoard.board.board.service.BoardService;
import com.studyBoard.board.post.PostService;
import com.studyBoard.board.post.domain.PostDTO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
public class PostController {
    private final PostService postService;
    private final BoardService boardService;

    public PostController(PostService postService, BoardService boardService) {
        this.postService = postService;
        this.boardService = boardService;
    }

//    @GetMapping("/posts/create")
//    public String showCreatePostForm(@RequestParam("boardId") Long boardId, Model model) {
//        Board board = boardService.getBoardById(boardId);
//        PostDTO postDTO = new PostDTO();
//        postDTO.setBoardId(boardId);
//        model.addAttribute("postDTO", postDTO);
//        return "post/createPost";
//    }
//    @PostMapping("/posts/create")
//    public String createPost(@ModelAttribute("postDTO") PostDTO postDTO, @RequestParam("boardId") Long boardId) {
//        postService.createPostWithBoardId1(postDTO, boardId);
//        return "redirect:/boards/" + boardId;
//    }


    @GetMapping("/posts/create")
    public String CreatePostForm(@RequestParam("boardId") Long boardId, Model model) {
        model.addAttribute("boardId", boardId); // 모델에 boardId 추가
        model.addAttribute("postDTO", new PostDTO()); //  PostDTO 객체 추가
        return "post/createPost";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute("postDTO") PostDTO postDTO, @RequestParam("boardId") Long boardId) {
        postService.createPostWithBoardId1(postDTO, boardId);
        return "redirect:/boards/" + boardId;
    }






}
