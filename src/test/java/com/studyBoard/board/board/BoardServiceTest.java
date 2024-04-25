package com.studyBoard.board.board;

import com.studyBoard.board.board.domain.Board;
import com.studyBoard.board.board.service.BoardService;
import com.studyBoard.board.post.domain.Post;
import com.studyBoard.board.board.repository.JdbcBoardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.description;
import static org.mockito.Mockito.verify;

public class BoardServiceTest {

    private BoardService boardService;

    @Mock
    private JdbcBoardRepository jdbcBoardRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        boardService = new BoardService(jdbcBoardRepository);
    }

    @Test
    public void createBoardTest() {
        // When
        String title = "Test Board";
        String description = "Tsest description2 description";
        List<Post> posts = new ArrayList<>();

        // Given
        boardService.saveBoard(title,description);

        // Then
        verify(jdbcBoardRepository).save(any(Board.class));
    }
}

