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

    @PostMapping("/userinfo")
    public EntityModel<UserInfoDto> newOne(@RequestBody UserInfoDto dto) {
        UserInfo newUserInfo = userInfoService.create(dto.getUID(), dto.getNickname(), dto.getOption());
        return userInfoAssembler.toModel(newUserInfo);
    }

    @PutMapping("/userinfo/{nickname}/{newNickname}")
    public ResponseEntity<?> replaceUserInfoByNickname(
            @PathVariable String nickname, @PathVariable String newNickname) {
        UserInfo replaceUserInfo = userInfoService.editUserNickname(nickname, newNickname);

        EntityModel<UserInfoDto> entityModel = userInfoAssembler.toModel(replaceUserInfo);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);

    }

    @PutMapping("/userinfo/{id}")
    public ResponseEntity<?> replaceUserInfo(
            @PathVariable Long id, @RequestBody UserInfoDto dto) {
        UserInfo replacedUserInfo = userInfoService.update(id, dto);

        EntityModel<UserInfoDto> entityModel = userInfoAssembler.toModel(replacedUserInfo);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

}
