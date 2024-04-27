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
//@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;
    private final PostService postService;
    public BoardController(BoardService boardService, PostService postService) {
        this.boardService = boardService;
        this.postService = postService;
    }

    /**
     * boards/boards 의 경로로 들어왔을 때 <p>
     * boards.html 뷰를 보여줌
     */

    @GetMapping("boards/boards")
    public String showBoardsForm(Model model) {
        List<Board> boards = boardService.getAllBoards();
        model.addAttribute("boards", boards);
        return "board/boards";
    }

    /**
     * boards/create 의 경로로 들어왔을 때 <p>
     * createBoard.html 뷰를 보여줌
     * */
    @GetMapping("boards/create")
    public String showCreateBoardForm(Model model) {
        model.addAttribute("boardDTO", new BoardDTO());
        return "board/createBoard";
    }

    /**
     * 게시글이 있는 특정 게시판을 보여줌 | post 가 만들어지지 않아서 오류
     * */
    @GetMapping("boards/{boardId}")
    public String showBoardDetails(@PathVariable Long boardId,
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
    @PostMapping("boards/create")
    public String createBoard(@ModelAttribute("boardDTO") BoardDTO boardDTO) {
        boardService.saveBoard(boardDTO.getTitle(), boardDTO.getDescription());
        return "redirect:/boards/boards"; // 해당 게시판 페이지로 리다이렉트 ???
    }

    /**
     * 게시판 수정 폼 보여주기
     * */
    @GetMapping("boards/{id}/edit")
    public String showEditBoardForm(@PathVariable Long id, Model model) {
        Board board = boardService.getBoardById(id);
        model.addAttribute("board", board);
        return "board/editBoard";
    }
    /**
     * 게시판 수정 동작
     * */
    @PostMapping ("boards/{id}/edit")
    public String editBoard(@PathVariable Long id, @ModelAttribute("board") BoardDTO boardDTO) {
        boardService.updateBoard(id, boardDTO.getTitle(), boardDTO.getDescription());
        return "redirect:/boards/boards";
    }


    /**
     * 특정 게시판 삭제 | post가 null인 오류
     * */
    @DeleteMapping("/boards/{id}/delete")
    public String deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return "redirect:/boards/boards";
    }

}
