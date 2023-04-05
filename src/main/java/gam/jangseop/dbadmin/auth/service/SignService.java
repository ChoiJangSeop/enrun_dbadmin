package gam.jangseop.dbadmin.auth.service;

import gam.jangseop.dbadmin.auth.domain.Admin;
import gam.jangseop.dbadmin.auth.domain.Authority;
import gam.jangseop.dbadmin.auth.domain.Status;
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
import java.util.stream.Collectors;

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
                .roles(findAdmin.getRoles().stream().map(Authority::getName).collect(Collectors.toList()))
                .status(findAdmin.getStatus())
                .build();
    }

    public SignResponse register(SignRequest request) throws Exception {
        try {
            Admin admin = Admin.builder()
                    .name(request.getName())
                    .account(request.getAccount())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .status(Status.NOT_ALLOWED)
                    .build();

            admin.setRoles(Collections.singletonList(Authority.builder().name("ROLE_All").build()));

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

    public SignResponse allowAdmin(String account) throws Exception {

        try {
            Admin findAdmin = adminRepository.findOneByAccount(account);
            findAdmin.allow();

            return new SignResponse(findAdmin);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("잘못된 계정 정보입니다.");
        }

    }


}
