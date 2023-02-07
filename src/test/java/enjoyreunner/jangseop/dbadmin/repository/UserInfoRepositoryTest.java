package enjoyreunner.jangseop.dbadmin.repository;

import enjoyreunner.jangseop.dbadmin.domain.UserInfo;
import enjoyreunner.jangseop.dbadmin.service.UserInfoService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserInfoRepositoryTest {

    // 아이유 9

    @Autowired UserInfoRepository userInfoRepository;
    @Autowired UserInfoService userInfoService;

    @Test
    @Transactional
    public void 유저정보_단건조회() throws Exception {
        // given
        Long id = userInfoRepository.save(UserInfo.createUserInfo("1234", "아이유", 1));

        // when
        UserInfo findUser = userInfoRepository.findOne(id);

        // then
        assertThat(findUser.getNickname()).isEqualTo("아이유");
    }

    @Test
    @Transactional
    public void 유저정보_닉네임조회() throws Exception {
        // given
        Long id = userInfoRepository.save(UserInfo.createUserInfo("1234", "아이유", 1));

        // when
        UserInfo findUser = userInfoRepository.findOneByNickname("아이유");

        // then
        assertThat(findUser.getId()).isEqualTo(id);
    }

    @Test
    @Transactional
    public void 유저정보_전체조회() throws Exception {
        // given
        userInfoRepository.save(UserInfo.createUserInfo("1234", "아이유", 1));
        userInfoRepository.save(UserInfo.createUserInfo("1235", "아이유1", 1));
        userInfoRepository.save(UserInfo.createUserInfo("1236", "아이유2", 1));

        // when
        List<UserInfo> allUserInfos = userInfoRepository.findAll();

        // then
        Assertions.assertThat(allUserInfos.size()).isEqualTo(3);
    }

}