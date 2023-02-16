package gam.jangseop.dbadmin.service;

import gam.jangseop.dbadmin.domain.Item;
import gam.jangseop.dbadmin.repository.ItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired private ItemRepository itemRepository;
    @Autowired private ItemService itemService;

    @Test
    public void 아이템_생성() throws Exception {
        // given

        // when
        Item test = itemService.create("test");

        // then
        Assertions.assertThat(itemRepository.findOneByName("test")).isEqualTo(test);
    }
}