package IO.SideQnA.board.dto;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class MessagePostDto {
    @NotBlank
    @Email
    private String email;

    @Pattern(regexp = "[0-9]{4}$",
            message = "비밀번호는 4자리 숫자여야합니다.")
    private String password;

    @NotBlank
    private String content;
}
