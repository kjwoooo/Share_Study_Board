package com.studyBoard.board.post.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.studyBoard.board.post.domain.Post;

@Getter @Setter
@NoArgsConstructor
public class PostDTO {

    private Long id;
    private String name;
    private String content;

    public Post toEntity() {
        return new Post(name, content);
    }
}

