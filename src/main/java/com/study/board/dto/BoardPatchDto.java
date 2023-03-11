package com.study.board.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class BoardPatchDto {
    @NotBlank(message = "내용은 공백이 아니어야 합니다.")
    private String contents;
}
