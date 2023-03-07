package gam.jangseop.dbadmin.auth.repository;

import gam.jangseop.dbadmin.auth.domain.Admin;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AdminRepositoryTest {

    @Autowired AdminRepository adminRepository;

    @Test
    @Transactional
    public void 관리자_단건조회() throws Exception {

        // given
        Admin admin = Admin.createAdmin("test", "test", "test");
        Long adminId = adminRepository.save(admin);

        // when
        Admin findAdmin = adminRepository.findOne(adminId);

        // then
        Assertions.assertThat(findAdmin).isEqualTo(admin);
    }

}