package com.studyBoard.board.post;

import com.studyBoard.board.board.domain.Board;
import com.studyBoard.board.board.repository.JdbcBoardRepository;
import com.studyBoard.board.post.domain.Post;
import com.studyBoard.board.post.domain.PostDTO;
import com.studyBoard.board.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private final PostRepository postRepository;
    private final JdbcBoardRepository jdbcBoardRepository;

    public PostService(PostRepository postRepository, JdbcBoardRepository jdbcBoardRepository) {
        this.postRepository = postRepository;
        this.jdbcBoardRepository = jdbcBoardRepository;
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
     * find Post by boardId
     */
    public List<Post> getPostsByBoardId(Long boardId) {
        List<Post> posts = postRepository.findByBoardId(boardId);
        return posts;
    }

    /**
     * Create Post1 with BoardId
     */
    public void createPostWithBoardId1(PostDTO postDTO, Long boardId) {
        Post post = new Post();
        post.setName(postDTO.getName());
        post.setContent(postDTO.getContent());
        post.setBoard(jdbcBoardRepository.findById(boardId).orElse(null));

        postRepository.save(post);
    }
    /**
     * Create Post2 with BoardId
     */
    public void createPostWithBoardId2(PostDTO postDTO) {
        Board board = jdbcBoardRepository.findById(postDTO.getId()).orElse(null);
            Post post = new Post(postDTO.getName(), postDTO.getContent());
            post.setBoard(board);
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
        Post post = postRepository.findById(postDTO.getId()).orElse(null);
        Post newPost = new Post();
        post.setName(postDTO.getName());
        post.setContent(postDTO.getContent());

        postRepository.save(newPost);
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
