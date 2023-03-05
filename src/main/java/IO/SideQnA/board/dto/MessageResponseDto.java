package IO.SideQnA.board.dto;

import IO.SideQnA.member.entity.Member;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MessageResponseDto {
    private long messageId;
    private String content;
    private String email;
}
