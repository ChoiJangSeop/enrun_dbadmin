package gam.jangseop.dbadmin.service;

import gam.jangseop.dbadmin.domain.Gift;
import gam.jangseop.dbadmin.repository.GiftRepository;
import gam.jangseop.dbadmin.repository.ItemRepository;
import gam.jangseop.dbadmin.repository.UserInfoRepository;
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
    private final ItemRepository itemRepository;

    @Transactional
    public Gift create(Long sendUserId, Long receiveUserId, Long giftItemId, LocalDateTime expireDate, String memo) {

        Gift gift = Gift.createGift(sendUserId, receiveUserId, giftItemId, expireDate, memo);
        String sendUserNickname = userInfoRepository.findOne(sendUserId).getNickname();
        gift.setSendUserNickname(sendUserNickname);

        giftRepository.save(gift);

        return gift;
    }

    @Transactional
    public Gift create(String sendUserNickname, String receiveUserNickname, String giftItemName, String memo, LocalDateTime expireDate) {



        Long sendUserId = (sendUserNickname.equals("_system")) ?
                3400L : userInfoRepository.findOneByNickname(sendUserNickname).getId();
        Long receiveUserId = userInfoRepository.findOneByNickname(receiveUserNickname).getId();
        Long giftItemId = itemRepository.findOneByName(giftItemName).getId();

        Gift gift = Gift.createGift(sendUserId, receiveUserId, giftItemId, expireDate, memo);
        gift.setSendUserNickname(sendUserNickname);
        if (sendUserNickname.equals("_system")) gift.setSendUserNickname("");

        giftRepository.save(gift);

        return gift;
    }

}
