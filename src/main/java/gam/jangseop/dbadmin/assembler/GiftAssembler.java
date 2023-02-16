package gam.jangseop.dbadmin.assembler;

import gam.jangseop.dbadmin.controller.GiftController;
import gam.jangseop.dbadmin.domain.Gift;
import gam.jangseop.dbadmin.dto.GiftDto;
import gam.jangseop.dbadmin.repository.ItemRepository;
import gam.jangseop.dbadmin.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@RequiredArgsConstructor
public class GiftAssembler implements RepresentationModelAssembler<Gift, EntityModel<GiftDto>> {

    private final UserInfoRepository userInfoRepository;
    private final ItemRepository itemRepository;

    @Override
    public EntityModel<GiftDto> toModel(Gift gift) {
        String receiveUserNickname = userInfoRepository.findOne(gift.getReceiveUserId()).getNickname();
        String giftItemName = itemRepository.findOne(gift.getGiftItemId()).getName();

        GiftDto dto = new GiftDto(
                gift.getSendUserNickname(),
                receiveUserNickname,
                giftItemName,
                gift.getMemo(),
                gift.getSendDate(),
                gift.getExpireDate()
        );

        return EntityModel.of(dto,
                linkTo(methodOn(GiftController.class).one(gift.getId())).withSelfRel());

    }
}
