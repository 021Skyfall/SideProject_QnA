package IO.SideQnA.board.mapper;

import IO.SideQnA.board.dto.MessagePostDto;
import IO.SideQnA.board.dto.MessageResponseDto;
import IO.SideQnA.board.entity.Message;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MessageMapper {
    Message messagePostDtoToMessage(MessagePostDto messagePostDto);
    MessageResponseDto messageToMessageResponseDto(Message message);
}
