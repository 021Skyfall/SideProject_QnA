package com.study.board.dto;

import lombok.Getter;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Getter
@Validated
public class BoardPostDto {
    private Long memberId;

    @NotBlank(message = "제목은 공백이 아니어야 합니다.")
    private String title;

    @NotBlank(message = "내용은 공백이 아니어야 합니다.")
    private String content;

    @Range(min = 1, max = 2, message = "1 = 공개글, 2 = 비밀글")
    private Integer accessLevel;
}
