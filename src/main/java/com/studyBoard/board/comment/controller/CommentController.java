package com.studyBoard.board.comment.controller;

import com.studyBoard.board.comment.domain.CommentDTO;
import com.studyBoard.board.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }



    /**
     * 특정 게시물에서 댓글 생성하기
     * */
    @PostMapping("/comments")
    public String addComment(@RequestParam Long postId, @RequestParam String content) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setContent(content);
        commentService.createCommentWithPostId(commentDTO, postId);
        return "redirect:/posts/" + postId;
    }

    @PostMapping("/comments/{commentId}/edit")
    public String editComment(@PathVariable Long commentId, @RequestParam String content) {
        commentService.updateComment1(commentId, content);
        return "redirect:/posts/" + commentService.getCommentById(commentId).getPost().getId();
    }

   @DeleteMapping("/comments/{commentId}")
        public String deleteComment(@PathVariable Long commentId) {
            commentService.deleteComment(commentId);
            return "redirect:/posts/" + commentService.getCommentById(commentId).getPost().getId();
        }









}
