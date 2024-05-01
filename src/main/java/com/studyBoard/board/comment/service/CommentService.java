package com.studyBoard.board.comment.service;

import com.studyBoard.board.comment.domain.Comment;
import com.studyBoard.board.comment.domain.CommentDTO;
import com.studyBoard.board.comment.repository.CommentRepository;
import com.studyBoard.board.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostService postService;
    @Autowired
    public CommentService(CommentRepository commentRepository, PostService postService) {
        this.commentRepository = commentRepository;
        this.postService = postService;
    }

    /**
     * get Comment
     * */
    public Comment getCommentById(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    /**
     * get Comment All
     * */
    public List<Comment> getCommnetAll() {
        return commentRepository.findAll();
    }


    /**
     * get Comments By PostId
     * */
    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }


    /**
     * create Comment With PostId
     * */
    public void createCommentWithPostId(CommentDTO commentDTO, Long postId) {
        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());
        comment.setPost(postService.getPostById(postId));

        commentRepository.save(comment);
    }


    /**
     * update Comment
     * */
//    public void updateComment(Long commentId, CommentDTO commentDTO) {
//        Optional<Comment> optionalComment = commentRepository.findById(commentId);
//        if (optionalComment.isPresent()) {
//            Comment comment = optionalComment.get();
//            comment.setContent(commentDTO.getContent());
//            commentRepository.save(comment);
//        }
//    }

    public void updateComment1(Long commentId, String newComment) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (comment != null) {
            comment.setContent(newComment);
            commentRepository.save(comment);
        }
    }




    /**
     * delete Comment
     * */
    public void deleteComment(Long commentId) {

        commentRepository.deleteById(commentId);
    }

    public Long getPostIdByCommentId(Long commentId) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (optionalComment.isPresent()) {
            return optionalComment.get().getPost().getId();
        }
        return null;
    }



}
