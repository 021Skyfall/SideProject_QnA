package IO.SideQnA.board.Repository;

import IO.SideQnA.board.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
