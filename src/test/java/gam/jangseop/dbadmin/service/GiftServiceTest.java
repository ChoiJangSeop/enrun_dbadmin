package gam.jangseop.dbadmin.service;

import gam.jangseop.dbadmin.domain.Gift;
import gam.jangseop.dbadmin.domain.UserInfo;
import gam.jangseop.dbadmin.repository.GiftRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class GiftServiceTest {

    @Autowired
    GiftRepository giftRepository;
    @Autowired GiftService giftService;
    @Autowired UserInfoService userInfoService;

    @Test
    public void 선물_생성() throws Exception {
        // given
        UserInfo userInfo = userInfoService.create("test", "test", 1);

        // when
        Gift gift = giftService.create(userInfo.getId(), 2L, 3L, LocalDateTime.now(), "memo");

        // then
        Assertions.assertThat(giftRepository.findOne(gift.getId())).isEqualTo(gift);
    }
}