package com.studyBoard.board.board.repository;

import com.studyBoard.board.board.domain.Board;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcBoardRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcBoardRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * CREATE (INSERT)
     */
    public void save(Board board) {
        String sql = "INSERT INTO board (title, description) VALUES (?, ?)";
        jdbcTemplate.update(sql, board.getTitle(), board.getDescription());
    }

    /**
     * READ by id (SELECT)
     */
    public Optional<Board> findById(Long id) {
        String sql = "SELECT * FROM board WHERE id = ?";
        try {
            Board board = jdbcTemplate.queryForObject(sql, new Object[]{id}, boardRowMapper());
            return Optional.ofNullable(board);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    /**
     * READ All (SELECT)
     */
    public List<Board> findAll() {
        String sql = "SELECT * FROM board";
        return jdbcTemplate.query(sql, boardRowMapper());
    }

    /**
     * UPDATE (UPDATE)
     */
    public void update(Board board) {
        String sql = "UPDATE board SET title = ?, description = ? WHERE id = ?";
        jdbcTemplate.update(sql, board.getTitle(), board.getDescription(), board.getId());
    }

    /**
     * DELETE by id(DELETE)
     */
    public void deleteById(Long id) {
        String sql = "DELETE FROM board WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private RowMapper<Board> boardRowMapper() {
        return (rs, rowNum) -> {
            Board board = new Board();
            board.setId(rs.getLong("id"));
            board.setTitle(rs.getString("title"));
            board.setDescription(rs.getString("description"));
            return board;
        };
    }
}
