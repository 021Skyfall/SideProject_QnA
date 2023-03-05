package IO.SideQnA.board.entity;

import IO.SideQnA.audit.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Message extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long messageId;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;
}
