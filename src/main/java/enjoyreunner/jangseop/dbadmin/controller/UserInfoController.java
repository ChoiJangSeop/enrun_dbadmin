package enjoyreunner.jangseop.dbadmin.controller;

import enjoyreunner.jangseop.dbadmin.assembler.UserInfoAssembler;
import enjoyreunner.jangseop.dbadmin.domain.UserInfo;
import enjoyreunner.jangseop.dbadmin.dto.UserInfoDto;
import enjoyreunner.jangseop.dbadmin.repository.UserInfoRepository;
import enjoyreunner.jangseop.dbadmin.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserInfoController {

    private final UserInfoRepository userInfoRepository;
    private final UserInfoService userInfoService;
    private final UserInfoAssembler userInfoAssembler;

    @GetMapping("/userinfo")
    public CollectionModel<EntityModel<UserInfoDto>> all() {
        List<EntityModel<UserInfoDto>> userInfos = userInfoRepository.findAll().stream()
                .map(userInfoAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(userInfos,
                linkTo(methodOn(UserInfoController.class).all()).withSelfRel());

    }

    @GetMapping("/userinfo/{id}")
    public EntityModel<UserInfoDto> one(@PathVariable Long id) {
        UserInfo findUserInfo = userInfoRepository.findOne(id);
        return userInfoAssembler.toModel(findUserInfo);
    }

    @PutMapping("/userinfo/{nickname}")
    public ResponseEntity<?> replaceUserInfoByNickname(
            @PathVariable String nickname, @RequestBody UserInfo userInfo) {
        UserInfo replaceUserInfo = userInfoService.editUserNickname(nickname, userInfo.getNickname());

        EntityModel<UserInfoDto> entityModel = userInfoAssembler.toModel(replaceUserInfo);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);

    }
}
