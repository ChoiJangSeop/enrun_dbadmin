package enjoyreunner.jangseop.dbadmin.service;

import enjoyreunner.jangseop.dbadmin.domain.UserInfo;
import enjoyreunner.jangseop.dbadmin.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserInfoService {

    private final UserInfoRepository userInfoRepository;

    @Transactional
    public UserInfo create(String UID, String nickname, int option) {
        UserInfo createdUserInfo = UserInfo.createUserInfo(UID, nickname, option);
        userInfoRepository.save(createdUserInfo);
        return createdUserInfo;
    }

    @Transactional
    public UserInfo editUserNickname(String nickname, String newNickname) {
        UserInfo findUser = userInfoRepository.findOneByNickname(nickname);
        findUser.setNickname(newNickname);

        return findUser;
    }
}
