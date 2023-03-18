package gam.jangseop.dbadmin.auth.service;

import gam.jangseop.dbadmin.auth.domain.Admin;
import gam.jangseop.dbadmin.auth.domain.Authority;
import gam.jangseop.dbadmin.auth.dto.SignRequest;
import gam.jangseop.dbadmin.auth.dto.SignResponse;
import gam.jangseop.dbadmin.auth.repository.AdminRepository;
import gam.jangseop.dbadmin.auth.util.JwtProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@Transactional
@RequiredArgsConstructor
public class SignService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;


    public SignResponse login(SignRequest request) throws Exception {
        Admin findAdmin = adminRepository.findOneByAccount(request.getAccount());

        if (findAdmin == null) {
            throw new BadCredentialsException("존재하지 않은 사용자입니다.");
        }

        if (!passwordEncoder.matches(request.getPassword(), findAdmin.getPassword())) {
            throw new BadCredentialsException("잘못된 비밀번호입니다.");
        }

        return SignResponse.builder()
                .id(findAdmin.getId())
                .name(findAdmin.getName())
                .account(findAdmin.getAccount())
                .token(jwtProvider.createToken(findAdmin.getAccount(), findAdmin.getRoles()))
                .roles(findAdmin.getRoles())
                .build();
    }

    public SignResponse register(SignRequest request) throws Exception {
        try {
            Admin admin = Admin.builder()
                    .name(request.getName())
                    .account(request.getAccount())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .build();

            admin.setRoles(Collections.singletonList(Authority.builder().name("All").build()));

            adminRepository.save(admin);
            return new SignResponse(admin);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("잘못된 요청입니다.");
        }
    }

    public SignResponse getAdmin(String account) throws Exception {
        Admin findAdmin = adminRepository.findOneByAccount(account);
        return new SignResponse(findAdmin);
    }



}
