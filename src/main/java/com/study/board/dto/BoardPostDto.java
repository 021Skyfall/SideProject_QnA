package com.study.board.dto;

import lombok.Getter;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Getter
@Validated
public class BoardPostDto {
    @Positive
    private Long memberId;

    @Pattern(regexp = "[0-9]{4}$")
    private String password;

    @NotBlank(message = "제목은 공백이 아니어야 합니다.")
    private String title;

    @NotBlank(message = "내용은 공백이 아니어야 합니다.")
    private String content;

    @Range(min = 1, max = 2)
    private Integer accessLevel;
}
