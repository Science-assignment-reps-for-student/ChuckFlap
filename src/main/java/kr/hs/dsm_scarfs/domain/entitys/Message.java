package kr.hs.dsm_scarfs.domain.entitys;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Integer id;

    @Column
    private Integer userId;

    @Column
    private String message;

    @Column
    private Integer messageType;

    @Column
    private Date messageTime;

    @Column
    private boolean isShow;

    @Builder
    public Message(Integer userId, String message, Integer messageType, Date messageTime, boolean isShow) {
        this.userId = userId;
        this.message = message;
        this.messageType = messageType;
        this.messageTime = messageTime;
        this.isShow = isShow;
    }
}
