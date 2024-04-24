package com.studyBoard.board.board.domain;

import com.studyBoard.board.post.domain.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOARD_Id")
    private Long id;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    /**
     * Post와 일대다 관계 <p>
     * board 와 매핑
     */
    @OneToMany(mappedBy = "board")
    private List<Post> posts = new ArrayList<>();

}
