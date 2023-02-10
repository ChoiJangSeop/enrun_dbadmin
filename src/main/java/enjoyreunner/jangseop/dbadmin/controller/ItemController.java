package enjoyreunner.jangseop.dbadmin.controller;

import enjoyreunner.jangseop.dbadmin.assembler.ItemAssembler;
import enjoyreunner.jangseop.dbadmin.domain.Item;
import enjoyreunner.jangseop.dbadmin.dto.ItemDto;
import enjoyreunner.jangseop.dbadmin.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemRepository itemRepository;
    private final ItemAssembler itemAssembler;

    @GetMapping("/items/{id}")
    public EntityModel<ItemDto> one(@PathVariable Long id) {
        Item findItem = itemRepository.findOne(id);
        return itemAssembler.toModel(findItem);
    }

    @GetMapping("/items")
    public CollectionModel<EntityModel<ItemDto>> all() {
        List<EntityModel<ItemDto>> items = itemRepository.findAll().stream()
                .map(itemAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(items,
                linkTo(methodOn(ItemController.class).all()).withSelfRel());
    }
}
