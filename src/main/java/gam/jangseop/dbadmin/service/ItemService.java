package gam.jangseop.dbadmin.service;

import gam.jangseop.dbadmin.domain.Item;
import gam.jangseop.dbadmin.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public Item create(String name) {
        Item item = Item.createItem(name);
        itemRepository.save(item);

        return item;
    }


}
