package enjoyreunner.jangseop.dbadmin.assembler;

import enjoyreunner.jangseop.dbadmin.controller.UserInfoController;
import enjoyreunner.jangseop.dbadmin.domain.UserInfo;
import enjoyreunner.jangseop.dbadmin.dto.UserInfoDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserInfoAssembler implements RepresentationModelAssembler<UserInfo, EntityModel<UserInfoDto>> {
    @Override
    public EntityModel<UserInfoDto> toModel(UserInfo userInfo) {
        UserInfoDto dto = new UserInfoDto(
                userInfo.getId(),
                userInfo.getNickname(),
                userInfo.getAttribute(),
                userInfo.getUID(),
                userInfo.getOption());
        return EntityModel.of(dto,
                linkTo(methodOn(UserInfoController.class).one(userInfo.getId())).withSelfRel(),
                linkTo(methodOn(UserInfoController.class).all()).withRel("userInfos"));
    }
}
