package com.studyBoard.board.post;

import com.studyBoard.board.post.domain.Post;
import com.studyBoard.board.post.domain.PostDTO;
import com.studyBoard.board.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    @Autowired
    private final PostRepository postRepository;
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    /**
     * Create Post
     * */
    public void createPost(PostDTO postDTO) {
        Post post = new Post();
        post.setName(postDTO.getName());
        post.setContent(postDTO.getContent());
        post.setId(postDTO.getId());

        postRepository.save(post);
    }

    /**
     * Save Post
     * */
    public Post savePost(PostDTO postDTO) {
        return postRepository.save(postDTO.toEntity());
    }

}
