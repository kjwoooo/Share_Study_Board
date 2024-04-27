package com.studyBoard.board.board.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardDTO {

    private Long id;
    private String title;
    private String description;

}
