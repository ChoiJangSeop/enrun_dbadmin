package enjoyreunner.jangseop.dbadmin.assembler;

import enjoyreunner.jangseop.dbadmin.controller.GiftController;
import enjoyreunner.jangseop.dbadmin.domain.Gift;
import enjoyreunner.jangseop.dbadmin.dto.GiftDto;
import enjoyreunner.jangseop.dbadmin.repository.ItemRepository;
import enjoyreunner.jangseop.dbadmin.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
