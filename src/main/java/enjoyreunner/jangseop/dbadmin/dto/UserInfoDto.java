package enjoyreunner.jangseop.dbadmin.dto;

import enjoyreunner.jangseop.dbadmin.domain.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDto {

    private Long id;
    private String nickname;
    private int attribute;
    private String UID;
    private int option;

    //== 비지니스 메서드 ==//
    public static UserInfoDto createDtoByUserInfo(UserInfo userInfo) {

        UserInfoDto newDto = new UserInfoDto();

        newDto.id = userInfo.getId();
        newDto.nickname = userInfo.getNickname();
        newDto.attribute = userInfo.getAttribute();
        newDto.UID = userInfo.getUID();
        newDto.option = userInfo.getOption();

        return newDto;
    }

}
