package com.studyBoard.board.comment.controller;

import com.studyBoard.board.comment.domain.CommentDTO;
import com.studyBoard.board.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }



    /**
     * 특정 게시물에서 댓글 생성
     * */
    @PostMapping("")
    public String createComment(@RequestParam Long postId, @RequestParam String content) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setContent(content);
        commentService.createCommentWithPostId(commentDTO, postId);
        return "redirect:/posts/" + postId;
    }

    /**
     * 특정 댓글 수정
     * */
    @PostMapping("/{commentId}/edit")
    public String editComment(@PathVariable Long commentId, @RequestParam String content) {
        commentService.updateComment(commentId, content);
        Long postId  = commentService.getPostIdByCommentId(commentId);
        return "redirect:/posts/" + postId;
    }

    /**
     * 특정 댓글 삭제
     * */
   @DeleteMapping("/{commentId}")
        public String deleteComment(@PathVariable Long commentId) {
            Long postId = commentService.getPostIdByCommentId(commentId);
            commentService.deleteComment(commentId);
            return "redirect:/posts/" + postId;
        }









}
