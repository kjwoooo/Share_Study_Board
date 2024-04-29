package com.studyBoard.board.post;

import com.studyBoard.board.board.domain.Board;
import com.studyBoard.board.board.repository.JdbcBoardRepository;
import com.studyBoard.board.board.service.BoardService;
import com.studyBoard.board.post.domain.Post;
import com.studyBoard.board.post.domain.PostDTO;
import com.studyBoard.board.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private final PostRepository postRepository;
    private final JdbcBoardRepository jdbcBoardRepository;
    private final BoardService boardService;

    public PostService(PostRepository postRepository, JdbcBoardRepository jdbcBoardRepository, BoardService boardService) {
        this.postRepository = postRepository;
        this.jdbcBoardRepository = jdbcBoardRepository;
        this.boardService = boardService;
    }

    /**
     * find Post By id
     */
    public Post getPostById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    /**
     * find All Post
     */
    public List<Post> getPostAll() {
        return postRepository.findAll();
    }

    /**
     * find Post by boardId | 페이지네이션
     */
    public Page<Post> getPostsByBoardId(Long boardId, Pageable pageable) {
        Page<Post> posts = postRepository.findByBoardId(boardId, pageable);
        return posts;
    }

    /**
     * Create Post1 with BoardId
     */
    public void createPostWithBoardId1(PostDTO postDTO, Long boardId) {
        Post post = new Post();
        post.setName(postDTO.getName());
        post.setContent(postDTO.getContent());
        post.setBoard(boardService.getBoardById(boardId));

        postRepository.save(post);
    }
    /**
     * Create Post2 with BoardId
     */
//    public void createPostWithBoardId2(PostDTO postDTO) {
//        Board board =boardService.getBoardById(postDTO.getId());
//                Post post = new Post(postDTO.getName(), postDTO.getContent());
//            post.setBoard(board);
//            postRepository.save(post);
//    }
//    public void createPostWithBoardId3(PostDTO postDTO, Long boardId) {
//        Board board = new Board();  // 새로운 게시판 생성
//        board.setId(boardId);       // URL에서 받은 파라미터 값으로 게시판 ID 설정
//        Post post = new Post();
//        post.setName(postDTO.getName());
//        post.setContent(postDTO.getContent());
//        post.setBoard(board);
//
//        postRepository.save(post);
//    }


    /**
     * Create Post
     */
    public void createPost(PostDTO postDTO) {
        Post post = new Post();
        post.setName(postDTO.getName());
        post.setContent(postDTO.getContent());
        post.setId(postDTO.getId());

        postRepository.save(post);
    }

    /**
     * update Post
     */
    public void updatePost(PostDTO postDTO) {
        Post post = postRepository.findById(postDTO.getId()).orElse(null);
        if (post != null) {
            post.setName(postDTO.getName());
            post.setContent(postDTO.getContent());
            postRepository.save(post);
        }
    }



    /**
     * Save Post
     */
    public Post savePost(PostDTO postDTO) {
        return postRepository.save(postDTO.toEntity());
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }


}
