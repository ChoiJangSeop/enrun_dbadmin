package enjoyreunner.jangseop.dbadmin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import enjoyreunner.jangseop.dbadmin.domain.UserInfo;
import enjoyreunner.jangseop.dbadmin.dto.UserInfoDto;
import enjoyreunner.jangseop.dbadmin.repository.UserInfoRepository;
import enjoyreunner.jangseop.dbadmin.service.UserInfoService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class UserInfoControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;

    @Autowired UserInfoService userInfoService;
    @Autowired UserInfoRepository userInfoRepository;

    @Test
    public void 유저정보_전체조회요청() throws Exception {
        // given
        userInfoService.create("testid1", "test1", 1);
        userInfoService.create("testid2", "test2", 1);
        userInfoService.create("testid3", "test3", 1);


        // when
        mockMvc.perform(get("/userinfo")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
        // then
                .andExpect(jsonPath("$._embedded.userInfoDtoList", hasSize(3)));
    }

    @Test
    public void 유저정보_단건조회요청() throws Exception {
        // given
        UserInfo userInfo = userInfoService.create("testid1", "test1", 1);

        // when
        mockMvc.perform(get("/userinfo/"+userInfo.getId().intValue())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
        // then
                .andExpect(jsonPath("$.id", is(userInfo.getId().intValue())));
    }

    @Test
    public void 유저정보_닉네임변경() throws Exception {
        // given
        UserInfo testUserInfo = userInfoService.create("testId", "test", 1);
        UserInfoDto dto = UserInfoDto.createDtoByUserInfo(testUserInfo);
        dto.setNickname("newTest");
        String content = objectMapper.writeValueAsString(dto);

        // when
        mockMvc.perform(put("/userinfo/"+testUserInfo.getNickname())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
        // then
                .andExpect(status().is2xxSuccessful());

        Assertions.assertThat(userInfoRepository.findOne(testUserInfo.getId()).getNickname()).isEqualTo("newTest");
    }

}