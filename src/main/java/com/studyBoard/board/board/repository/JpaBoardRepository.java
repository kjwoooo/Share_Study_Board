package com.studyBoard.board.board.repository;

import com.studyBoard.board.board.domain.Board;
import com.studyBoard.board.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaBoardRepository  extends JpaRepository<Board, Long> {
}
