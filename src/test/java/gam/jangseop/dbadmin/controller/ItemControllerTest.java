package gam.jangseop.dbadmin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import gam.jangseop.dbadmin.domain.Item;
import gam.jangseop.dbadmin.repository.ItemRepository;
import gam.jangseop.dbadmin.service.ItemService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ItemControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;

    @Autowired
    ItemRepository itemRepository;
    @Autowired
    ItemService itemService;

    @Test
    public void 아이템_전체조회요청() throws Exception {
        // given
        itemService.create("test1");
        itemService.create("test2");
        itemService.create("test3");

        // when
        mockMvc.perform(get("/items")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
        // then
                .andExpect(jsonPath("$._embedded.itemDtoList", hasSize(3)));
    }

    @Test
    public void 아이템_단건조회요청() throws Exception {
        // given
        Item item1 = itemService.create("item1");
        Item item2 = itemService.create("item2");

        // when
        mockMvc.perform(get("/items/" + item1.getId().intValue())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
        // then
                .andExpect(jsonPath("$.id", is(item1.getId().intValue())));
    }

    @Test
    public void 아이템_단건조회이름() throws Exception {
        // given
        Item item1 = itemService.create("item1");

        // when
        mockMvc.perform(get("/items/name/item1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
        // then
                .andExpect(jsonPath("$.id", is(item1.getId().intValue())));
    }

    @Test
    public void 아이템_검색() throws Exception {
        // given
        Item 빨간떡볶이 = itemService.create("빨간떡볶이");
        Item 초록떡볶이 = itemService.create("초록떡볶이");
        Item 빨간주먹밥 = itemService.create("빨간주먹밥");

        // when
        mockMvc.perform(get("/items/search/빨간")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
        // then
                .andExpect(jsonPath("$._embedded.itemDtoList", hasSize(2)));
    }
}