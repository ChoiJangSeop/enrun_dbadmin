package enjoyreunner.jangseop.dbadmin.service;

import enjoyreunner.jangseop.dbadmin.domain.Gift;
import enjoyreunner.jangseop.dbadmin.repository.GiftRepository;
import enjoyreunner.jangseop.dbadmin.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GiftService {

    private final GiftRepository giftRepository;
    private final UserInfoRepository userInfoRepository;

    @Transactional
    public Gift create(Long sendUserId, Long receiveUserId, Long giftItemId, LocalDateTime expireDate, String memo) {

        Gift gift = Gift.createGift(sendUserId, receiveUserId, giftItemId, expireDate, memo);
        String sendUserNickname = userInfoRepository.findOne(sendUserId).getNickname();
        gift.setSendUserNickname(sendUserNickname);

        giftRepository.save(gift);

        return gift;
    }

}
