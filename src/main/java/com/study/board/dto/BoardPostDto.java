package com.study.board.dto;

import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Validated
public class BoardPostDto {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Pattern(regexp = "[0-9]{4}$") // Spring 타입에서만 씀
    private String password;

    @NotBlank(message = "내용은 공백이 아니어야 합니다.")
    private String content;
}
