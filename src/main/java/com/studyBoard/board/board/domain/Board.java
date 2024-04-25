package com.studyBoard.board.board.domain;

import com.studyBoard.board.audit.BaseEntity;
import com.studyBoard.board.post.domain.Post;
import jakarta.persistence.*;
import lombok.Builder;
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
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOARD_Id")
    private Long id;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    /**
     * Post와 일대다 관계 <p>
     * board 와 매핑
     */
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    @Builder
    public Board( String title, String description, List<Post> posts ){
        this.title = title;
        this.description= description;
        this.posts = posts;
    }

}
