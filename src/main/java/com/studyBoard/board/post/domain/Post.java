package com.studyBoard.board.post.domain;

import com.studyBoard.board.audit.BaseEntity;
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
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String content;

    /**
     * Board와 다대일 관계 <p>
     * BOARD_ID 와 매핑
     */
    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;


    /**
     * Post의 name, content 로
     * 초기화 되는 생성자
     */
    public Post(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public Post(String name, String content, Long boardId) {
        this.name = name;
        this.content = content;
        this.board.setId(boardId);
    }


}
