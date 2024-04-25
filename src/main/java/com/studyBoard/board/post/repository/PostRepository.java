package com.studyBoard.board.post.repository;

import com.studyBoard.board.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAll();

    Optional<Post> findById(Long id);


    Post save(Post post);

    void delete(Post post);
}
