package com.studyBoard.board.comment.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDTO {

    private Long id;
    private String content;
    private Long postId;

    public CommentDTO(Long id, String content, Long postId) {
        this.id = id;
        this.content = content;
        this.postId = postId;
    }
}
