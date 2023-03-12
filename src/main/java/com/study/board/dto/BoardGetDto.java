package com.study.board.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Getter
public class BoardGetDto {
    @Setter
    private Long boardId;
    @Positive
    private Long memberId;
    @Pattern(regexp = "[0-9]{4}$")
    private String password;
}
