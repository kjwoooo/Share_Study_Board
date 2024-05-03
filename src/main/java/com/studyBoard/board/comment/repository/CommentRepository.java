package com.studyBoard.board.comment.repository;

import com.studyBoard.board.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPostId(Long postId);

    List<Comment> findAll();

    Optional<Comment> findById(Long id);

    Comment save(Comment comment);

    void delete(Comment comment);


}
