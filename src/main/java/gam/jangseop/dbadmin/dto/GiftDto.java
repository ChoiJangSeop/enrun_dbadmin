package gam.jangseop.dbadmin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class GiftDto {

    private String sendUserNickname;
    private String receiveUserNickname;
    private String giftItemName;
    private String memo;
    private LocalDateTime sendDate;
    private LocalDateTime expireDate;
}
