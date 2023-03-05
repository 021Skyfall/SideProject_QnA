package IO.SideQnA.board.Controller;

import IO.SideQnA.board.dto.MessagePostDto;
import IO.SideQnA.board.entity.Message;
import IO.SideQnA.board.mapper.MessageMapper;
import IO.SideQnA.board.service.MessageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boards")
@AllArgsConstructor
@Validated
@Slf4j
public class BoardController {
    private final MessageService service;
    private final MessageMapper mapper;

    @PostMapping
    public ResponseEntity postMessage(@Validated @RequestBody MessagePostDto messagePostDto) {

        Message content = service.createMessage(mapper.messagePostDtoToMessage(messagePostDto));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
