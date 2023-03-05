package IO.SideQnA.board.service;

import IO.SideQnA.board.Repository.MessageRepository;
import IO.SideQnA.board.entity.Message;
import IO.SideQnA.member.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageService {
    private final MessageRepository repository;
    private final MemberService memberService;

    public Message createMessage(Message message) {
        memberService.verifiedEmailAndPassword(message.getEmail(),message.getPassword());

        return repository.save(message);
    }
}
