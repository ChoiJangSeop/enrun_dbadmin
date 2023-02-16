package gam.jangseop.dbadmin.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "tblgift")
public class Gift {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fdnum")
    private Long id;

    @Column(name = "fdsendusernum")
    private Long sendUserId;

    @Column(name = "fdsendnickname")
    private String sendUserNickname;

    @Column(name = "fdreceiveusernum")
    private Long receiveUserId;

    @Column(name = "fdgiftitemdescnum")
    private Long giftItemId;

    @Column(name = "fdnotified")
    private Boolean notified;

    @Column(name = "fdsenddatetime")
    private LocalDateTime sendDate;

    @Column(name = "fdmemo")
    private String memo;

    @Column(name = "fdexpiredate")
    private LocalDateTime expireDate;


    //== 생성 메서드 ==//

    public static Gift createGift(Long sendUserId, Long receiveUserId, Long giftItemId, LocalDateTime expireDate, String memo) {
        Gift gift = new Gift();
        gift.sendUserId = sendUserId;
        gift.receiveUserId = receiveUserId;
        gift.giftItemId = giftItemId;
        gift.notified = false;
        gift.sendDate = LocalDateTime.now();
        gift.memo = memo;
        gift.expireDate = expireDate;
        return gift;
    }
}
