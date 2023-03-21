package gam.jangseop.dbadmin.auth.dto;

import gam.jangseop.dbadmin.auth.domain.Admin;
import gam.jangseop.dbadmin.auth.domain.Authority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
@Builder
@AllArgsConstructor
public class SignResponse {

    private Long id;

    private String name;

    private String account;

    private String password;

    private List<String> roles = new ArrayList<>();

    private String token;

    public SignResponse(Admin admin) {
        this.id = admin.getId();
        this.name = admin.getName();
        this.account = admin.getAccount();
        this.password = admin.getPassword();
        this.roles = admin.getRoles().stream()
                .map(Authority::getName)
                .collect(Collectors.toList());
    }
}
