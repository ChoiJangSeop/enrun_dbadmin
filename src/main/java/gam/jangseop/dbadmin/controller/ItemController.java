package gam.jangseop.dbadmin.controller;

import gam.jangseop.dbadmin.assembler.ItemAssembler;
import gam.jangseop.dbadmin.domain.Item;
import gam.jangseop.dbadmin.dto.ItemDto;
import gam.jangseop.dbadmin.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "http://localhost:5173")
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

    @GetMapping("/items/name/{name}")
    public EntityModel<ItemDto> oneByName(@PathVariable String name) {
        Item findItem = itemRepository.findOneByName(name);
        return itemAssembler.toModel(findItem);
    }

    @GetMapping("/items/search/{keyword}")
    public CollectionModel<EntityModel<ItemDto>> search(@PathVariable String keyword) {
        List<EntityModel<ItemDto>> items = itemRepository.findAll().stream()
                .filter(item -> item.getName().contains(keyword))
                .map(itemAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(items,
                linkTo(methodOn(ItemController.class).search(keyword)).withSelfRel());
    }
}
