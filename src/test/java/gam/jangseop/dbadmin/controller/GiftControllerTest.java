package gam.jangseop.dbadmin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import gam.jangseop.dbadmin.domain.Item;
import gam.jangseop.dbadmin.domain.UserInfo;
import gam.jangseop.dbadmin.dto.GiftDto;
import gam.jangseop.dbadmin.repository.GiftRepository;
import gam.jangseop.dbadmin.repository.ItemRepository;
import gam.jangseop.dbadmin.repository.UserInfoRepository;
import gam.jangseop.dbadmin.service.GiftService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class GiftControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;

    @Autowired
    GiftRepository giftRepository;
    @Autowired GiftService giftService;

    @Autowired
    ItemRepository itemRepository;
    @Autowired
    UserInfoRepository userInfoRepository;

    @Test
    public void 선물_단건생성요청() throws Exception {
        // given
        Item item1 = Item.createItem("item1");
        Item item2 = Item.createItem("item2");
        itemRepository.save(item1);
        itemRepository.save(item2);

        UserInfo user1 = UserInfo.createUserInfo("id1", "user1", 1);
        UserInfo user2 = UserInfo.createUserInfo("id2", "user2", 1);
        userInfoRepository.save(user1);
        userInfoRepository.save(user2);

        GiftDto dto = new GiftDto("user1", "user2", "item1", "memo", LocalDateTime.now(), LocalDateTime.now());
        String content = objectMapper.writeValueAsString(dto);

        // when
        mockMvc.perform(post("/gifts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print());
        // then
    }


}