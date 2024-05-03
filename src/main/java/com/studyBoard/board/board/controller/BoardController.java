package com.studyBoard.board.board.controller;

import com.studyBoard.board.board.domain.Board;
import com.studyBoard.board.board.domain.BoardDTO;
import com.studyBoard.board.board.service.BoardService;
import com.studyBoard.board.post.PostService;
import com.studyBoard.board.post.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;
    private final PostService postService;
    public BoardController(BoardService boardService, PostService postService) {
        this.boardService = boardService;
        this.postService = postService;
    }

    /**
     * 게시판 메인 폼
     */

    @GetMapping("/boards")
    public String BoardsForm(Model model) {
        List<Board> boards = boardService.getAllBoards();
        model.addAttribute("boards", boards);
        return "board/boards";
    }

    /**
     * 게시판 추가 폼
     * */
    @GetMapping("/create")
    public String CreateBoardForm(Model model) {
        model.addAttribute("boardDTO", new BoardDTO());
        return "board/createBoard";
    }

    /**
     * 게시글이 있는 특정 게시판 폼
     * */
    @GetMapping("/{boardId}")
    public String BoardDetails(@PathVariable Long boardId,
                                   @RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Post> postPage =  postService.getPostsByBoardId(boardId, pageable);
        Board board = boardService.getBoardById(boardId);
        model.addAttribute("postPage", postPage);
        model.addAttribute("board", board);
        return "board/board"; //
    }

    /**
     * 게시판 추가 | 추가후 게시판 목록 페이지로 이동
     * */
    @PostMapping("/create")
    public String createBoard(@ModelAttribute("boardDTO") BoardDTO boardDTO) {
        boardService.saveBoard(boardDTO.getTitle(), boardDTO.getDescription());
        return "redirect:/boards/boards"; // 해당 게시판 페이지로 리다이렉트 ???
    }

    /**
     * 게시판 수정 폼
     * */
    @GetMapping("/{boardId}/edit")
    public String EditBoardForm(@PathVariable Long boardId, Model model) {
        Board board = boardService.getBoardById(boardId);
        model.addAttribute("board", board);
        return "board/editBoard";
    }
    /**
     * 게시판 수정
     * */
    @PostMapping ("/{boardId}/edit")
    public String editBoard(@PathVariable Long boardId, @ModelAttribute("board") BoardDTO boardDTO) {
        boardService.updateBoard(boardId, boardDTO.getTitle(), boardDTO.getDescription());
        return "redirect:/boards/boards";
    }


    /**
     * 특정 게시판 삭제
     * */
    @DeleteMapping("/{boardId}/delete")
    public String deleteBoard(@PathVariable Long boardId) {
        boardService.deleteBoard(boardId);
        return "redirect:/boards/boards";
    }

}
