package com.studyBoard.board.board.controller;

import com.studyBoard.board.board.domain.BoardDTO;
import com.studyBoard.board.board.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@Controller
public class BoardController {
    private final BoardService boardService;
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/createBoard") // 수정된 부분
    public String showCreateBoardForm(){
        return "createBoard";
    }


    @PostMapping("/boards/create") // 경로 수정
    public ResponseEntity<String> createBoard(@RequestParam("title") String title, @RequestParam("description") String description) {
        try {
            boardService.saveBoard(title, description);
            return ResponseEntity.ok("게시판이 성공적으로 생성되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("게시판 생성에 실패했습니다.");
        }
    }


    @PutMapping("/board/{id}")
    public ResponseEntity<String> updateBoard(@PathVariable Long id, @RequestParam String newTitle, @RequestParam String newDescription) {
        try {
            boardService.updateBoard(id, newTitle, newDescription);
            return ResponseEntity.ok("게시판이 성공적으로 업데이트 되었습니다.");
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("게시판 업데이트에 실패했습니다.");
        }
    }

    @DeleteMapping("/board/{id}")
    public void deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
    }


}
