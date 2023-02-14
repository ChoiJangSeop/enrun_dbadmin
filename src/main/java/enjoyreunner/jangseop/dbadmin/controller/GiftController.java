package enjoyreunner.jangseop.dbadmin.controller;

import enjoyreunner.jangseop.dbadmin.assembler.GiftAssembler;
import enjoyreunner.jangseop.dbadmin.domain.Gift;
import enjoyreunner.jangseop.dbadmin.dto.GiftDto;
import enjoyreunner.jangseop.dbadmin.repository.GiftRepository;
import enjoyreunner.jangseop.dbadmin.service.GiftService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class GiftController {

    private final GiftRepository giftRepository;
    private final GiftService giftService;
    private final GiftAssembler giftAssembler;

    @GetMapping("/gifts/{id}")
    public EntityModel<GiftDto> one(@PathVariable Long id) {
        Gift findGift = giftRepository.findOne(id);
        return giftAssembler.toModel(findGift);
    }

    @PostMapping("/gifts")
    public EntityModel<GiftDto> newOne(@RequestBody GiftDto giftDto) {
        Gift gift = giftService.create(
                giftDto.getSendUserNickname(),
                giftDto.getReceiveUserNickname(),
                giftDto.getGiftItemName(),
                giftDto.getMemo(),
                giftDto.getExpireDate());
        return giftAssembler.toModel(gift);
    }
}
