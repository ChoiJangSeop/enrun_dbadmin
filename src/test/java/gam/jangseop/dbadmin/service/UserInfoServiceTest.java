package gam.jangseop.dbadmin.service;

import gam.jangseop.dbadmin.domain.UserInfo;
import gam.jangseop.dbadmin.repository.UserInfoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class UserInfoServiceTest {

    @Autowired
    UserInfoRepository userInfoRepository;
    @Autowired UserInfoService userInfoService;


    @Test
    @Transactional
    public void 유저정보_생성() throws Exception {
        // given
        UserInfo userInfo = userInfoService.create("1234", "아이유", 1);

        // when
        UserInfo findUserInfo = userInfoRepository.findOne(userInfo.getId());

        // then
        Assertions.assertThat(findUserInfo).isEqualTo(userInfo);
    }
    @Test
    @Transactional
    public void 유저정보_닉네임변경() throws Exception {
        // given
        userInfoService.create("1234", "아이유", 1);

        // when
        UserInfo findUserInfo = userInfoService.editUserNickname("아이유", "아이유2");

        // then
        Assertions.assertThat(userInfoRepository.findOneByNickname("아이유2")).isEqualTo(findUserInfo);
    }
}