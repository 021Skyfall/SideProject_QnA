package com.study.board.dto;

import com.study.validator.NotSpace;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Getter
public class BoardPatchDto {
    @Setter
    private Long boardId;

    private Long memberId;

    @NotSpace(message = "제목은 공백이 아니어야 합니다.")
    private String title;

    @NotSpace(message = "내용은 공백이 아니어야 합니다.")
    private String content;

    @Range(min = 1, max = 2, message = "1 = 공개글, 2 = 비밀글")
    private Integer accessLevel;
}
