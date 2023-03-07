package gam.jangseop.dbadmin.auth.service;


import gam.jangseop.dbadmin.auth.domain.Admin;
import gam.jangseop.dbadmin.auth.repository.AdminRepository;
import gam.jangseop.dbadmin.auth.util.SHA256;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminService {

    private final AdminRepository adminRepository;
    private final SHA256 sha256;

    /**
     * 생성
     */
    @Transactional
    public Admin create(String name, String account, String password) throws NoSuchAlgorithmException {

        validateAccountDuplicate(account);

        String encrypted = sha256.encryption(password);
        Admin admin = Admin.createAdmin(name, account, encrypted);

        adminRepository.save(admin);

        return admin;
    }

    // 아이디 중복 검증
    private void validateAccountDuplicate(String account) {
        Admin findAccount = adminRepository.findOneByAccount(account);

        if (findAccount != null) {
            throw new IllegalStateException("중복된 아이디가 존재합니다.");
        }
    }

    /**
     * 로그인
     */
    public Admin login(String account, String password) throws NoSuchAlgorithmException {
        String encrypted = sha256.encryption(password);

        Admin admin = adminRepository.findOneByAccount(account);

        if (admin == null) return null;

        if (admin.login(account, encrypted)) return admin;
        else return null;
    }

    /**
     * 유저명 변경
     */
    @Transactional
    public Admin updateName(Long adminId, String newName) {
        Admin findAdmin = adminRepository.findOne(adminId);
        findAdmin.updateAll(newName, findAdmin.getAccount(), findAdmin.getPassword());

        return findAdmin;
    }

    /**
     * 비밀번호 변경
     */
    @Transactional
    public Admin updatePassword(Long adminId, String password, String newPassword) throws NoSuchAlgorithmException {

        Admin findAdmin = adminRepository.findOne(adminId);

        if (findAdmin.getPassword().equals(sha256.encryption(password))) {
            findAdmin.updateAll(findAdmin.getName(), findAdmin.getAccount(), sha256.encryption(newPassword));
            return findAdmin;
        }

        return null;
    }
}
