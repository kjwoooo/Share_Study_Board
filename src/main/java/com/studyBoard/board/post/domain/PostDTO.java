package com.studyBoard.board.post.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.studyBoard.board.post.domain.Post;

@Getter
@Setter
@NoArgsConstructor
public class PostDTO {

    private Long id;
    private String name;
    private String content;
    private Long boardId; // 게시물이 속할 게시판의 아이디

    public Post toEntity() {
        return new Post(name, content, boardId);
    }
}

