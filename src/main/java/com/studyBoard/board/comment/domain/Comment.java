package com.studyBoard.board.comment.domain;

import com.studyBoard.board.post.domain.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_ID")
    private Long id;

    @Column(name = "CONTENT")
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;


    /**
     * Post와 다대일 단방향 관계
     */
    @ManyToOne
    @JoinColumn(name = "POST_ID")
    private Post post;


    /**
     * content(댓글의 내용), post(해당 게시글) <p>
     * 을 이용하여 Comment(댓글)생성
     */
    public Comment(String content, Post post) {
        this.content = content;
        this.post = post;
    }
}
