package com.studyBoard.board.board.domain;

import com.studyBoard.board.audit.BaseEntity;
import com.studyBoard.board.post.domain.Post;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "board")
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    /**
     * Post와 일대다 관계 <p>
     * board 와 매핑
     */
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    @Builder
    public Board(String title, String description, List<Post> posts) {
        this.title = title;
        this.description = description;
        this.posts = posts;
    }

}
