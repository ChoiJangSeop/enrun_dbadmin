package gam.jangseop.dbadmin.auth.service;

import gam.jangseop.dbadmin.auth.domain.Admin;
import gam.jangseop.dbadmin.auth.domain.CustomUserDetails;
import gam.jangseop.dbadmin.auth.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JpaUserDetailService implements UserDetailsService {

    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin findAdmin = adminRepository.findOneByAccount(username);

        if (findAdmin != null) {
            return new CustomUserDetails(findAdmin);
        } else {
            throw new UsernameNotFoundException("invalid authentication");
        }
    }
}
