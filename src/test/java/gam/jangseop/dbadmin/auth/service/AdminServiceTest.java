package gam.jangseop.dbadmin.auth.service;

import gam.jangseop.dbadmin.auth.domain.Admin;
import gam.jangseop.dbadmin.auth.repository.AdminRepository;
import gam.jangseop.dbadmin.auth.util.SHA256;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class AdminServiceTest {

    @Autowired
    AdminService adminService;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    SHA256 sha256;

    @Test
    public void 관리자_생성() throws Exception {
        // given

        // when
        Admin admin = adminService.create("testName", "testId", "testPwd");

        // then
        assertThat(admin).isEqualTo(adminRepository.findOne(admin.getId()));
    }

    @Test()
    public void 관리자_생성_이름중복() throws Exception {
        // given
        adminService.create("testName", "testId", "testPwd");

        // when

        // then
        org.junit.jupiter.api.Assertions.assertThrows(IllegalStateException.class, () -> {
            adminService.create("testName", "testId", "testPwd");
        });
    }

    @Test
    public void 관리자_로그인() throws Exception {
        // given
        Admin admin = adminService.create("testName", "testId", "testPwd");

        // when
        Admin wrongId = adminService.login("testIdd", "testPwd");
        Admin wrongPwd = adminService.login("testId", "testPwdd");
        Admin correct = adminService.login("testId", "testPwd");

        // then
        assertThat(wrongId).isNull();
        assertThat(wrongPwd).isNull();
        assertThat(correct).isEqualTo(admin);

    }

    @Test
    public void 관리자_비밀번호변경_실패() throws Exception {
        // given
        Admin admin = adminService.create("testName", "testId", "testPwd");

        // when
        Admin wrong = adminService.updatePassword(admin.getId(), "testPwdd", "newPwd");

        // then
        assertThat(wrong).isNull();
    }

    @Test
    public void 관리자_비밀번호변경() throws Exception {
        // given
        Admin admin = adminService.create("testName", "testId", "testPwd");

        // when
        adminService.updatePassword(admin.getId(), "testPwd", "newPwd");

        // then
        assertThat(sha256.encryption("newPwd")).isEqualTo(admin.getPassword());
    }
}