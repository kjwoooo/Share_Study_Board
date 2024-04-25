package com.studyBoard.board.board.service;

import com.studyBoard.board.board.domain.Board;
import com.studyBoard.board.board.repository.JdbcBoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    private final JdbcBoardRepository jdbcBoardRepository;

    public BoardService(JdbcBoardRepository jdbcBoardRepository) {
        this.jdbcBoardRepository = jdbcBoardRepository;
    }

    /**
     * Create Board
     */
    public void saveBoard(String title, String description) {
        Board board = Board.builder()
                .title(title)
                .description(description)
                .build();
        jdbcBoardRepository.save(board);
    }

    /**
     * Find all Boards
     */
    public List<Board> getAllBoards() {
        return jdbcBoardRepository.findAll();
    }

    /**
     * Find Board By id
     */
    public Board getBoardById(Long id) {
        return jdbcBoardRepository.findById(id).orElse(null);
    }

    /**
     * Update Board
     */
    public void updateBoard(Long id, String newTitle, String newDescription) {
        Optional<Board> originBoard = jdbcBoardRepository.findById(id);
        originBoard.ifPresent(board -> {
            board.setTitle(newTitle);
            board.setDescription(newDescription);
            jdbcBoardRepository.save(board);
        });
    }

    /**
     * Delete Board
     */
    public void deleteBoard(Long id) {
        jdbcBoardRepository.deleteById(id);
    }
}
