package com.studyBoard.board.post.domain;

import com.studyBoard.board.board.domain.Board;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "CONTENT", nullable = false)
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    /**
     * Board와 다대일 관계 <p>
     * BOARD_ID 와 매핑
     */
    @ManyToOne
    @JoinColumn(name = "BOARD_ID")
    private Board board;


    /**
     * Post의 name, content 로
     * 초기화 되는 생성자
     */
    public Post(String name, String content) {
        this.name = name;
        this.content = content;
    }


}
