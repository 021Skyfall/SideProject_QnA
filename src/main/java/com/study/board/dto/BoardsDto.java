package com.study.board.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
public class BoardsDto {
    @Positive
    private Long memberId;
    @NotBlank
    private String password;
}
