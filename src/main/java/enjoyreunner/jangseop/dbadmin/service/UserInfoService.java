package enjoyreunner.jangseop.dbadmin.service;

import enjoyreunner.jangseop.dbadmin.domain.UserInfo;
import enjoyreunner.jangseop.dbadmin.dto.UserInfoDto;
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

    // TODO UID/nickname 중복 검증

    @Transactional
    public UserInfo editUserNickname(String nickname, String newNickname) {
        UserInfo findUser = userInfoRepository.findOneByNickname(nickname);
        findUser.setNickname(newNickname);

        return findUser;
    }

    @Transactional
    public UserInfo update(Long id, UserInfoDto dto) {
        UserInfo findUser = userInfoRepository.findOne(id);
        findUser.setAll(dto.getUID(), dto.getNickname(), dto.getOption());

        return findUser;
    }
}
