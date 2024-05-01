package com.studyBoard.board.post;

import com.studyBoard.board.board.repository.JdbcBoardRepository;
import com.studyBoard.board.board.service.BoardService;
import com.studyBoard.board.comment.domain.Comment;
import com.studyBoard.board.comment.repository.CommentRepository;
import com.studyBoard.board.post.domain.Post;
import com.studyBoard.board.post.domain.PostDTO;
import com.studyBoard.board.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {


    private final PostRepository postRepository;
    private final JdbcBoardRepository jdbcBoardRepository;
    private final BoardService boardService;
    private final CommentRepository commentRepository;
    @Autowired
    public PostService(PostRepository postRepository, JdbcBoardRepository jdbcBoardRepository, BoardService boardService, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.jdbcBoardRepository = jdbcBoardRepository;
        this.boardService = boardService;
        this.commentRepository = commentRepository;
    }

    /**
     * find Post By id
     */
    public Post getPostById(Long id) {
        return postRepository.findById(id).orElse(null);
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
        Optional<Post> optionalPost = postRepository.findById(postDTO.getId());
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setName(postDTO.getName());
            post.setContent(postDTO.getContent());
            postRepository.save(post);
        } else {
            throw new IllegalArgumentException("Post not found with id: " + postDTO.getId());
        }
    }

    /**
     * Save Post
     */
    public Post savePost(PostDTO postDTO) {
        return postRepository.save(postDTO.toEntity());
    }

    /**
     * delete Post
     */
    public void deletePost(Long id) {
        List<Comment> comments = commentRepository.findAll();
        commentRepository.deleteAll(comments);
        postRepository.deleteById(id);
    }

    /**
     * get Post BoardId
     */
    public Long getPostBoardId(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("Post not found with id: " + postId));
        return post.getBoard().getId();
    }


}
