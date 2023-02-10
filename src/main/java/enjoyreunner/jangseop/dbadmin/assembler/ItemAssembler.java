package enjoyreunner.jangseop.dbadmin.assembler;

import enjoyreunner.jangseop.dbadmin.controller.ItemController;
import enjoyreunner.jangseop.dbadmin.domain.Item;
import enjoyreunner.jangseop.dbadmin.dto.ItemDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ItemAssembler implements RepresentationModelAssembler<Item, EntityModel<ItemDto>> {

    @Override
    public EntityModel<ItemDto> toModel(Item item) {
        ItemDto dto = new ItemDto(item.getId(), item.getName());

        return EntityModel.of(dto,
                linkTo(methodOn(ItemController.class).one(item.getId())).withSelfRel(),
                linkTo(methodOn(ItemController.class).all()).withRel("items"));
    }
}
