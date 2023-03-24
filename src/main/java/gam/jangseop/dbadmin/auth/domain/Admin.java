package gam.jangseop.dbadmin.auth.domain;

import gam.jangseop.dbadmin.auth.util.SHA256;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder @NoArgsConstructor @AllArgsConstructor
@Table(name = "admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADMIN_ID")
    private Long id;

    private String name;

    private String account;

    private String password;

    @OneToMany(mappedBy = "admin", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Builder.Default
    private List<Authority> roles = new ArrayList<>();

    //== 생성 메서드 ==//

    public static Admin createAdmin(String name, String account, String encryptedPassword) {

        Admin admin = new Admin();

        admin.name = name;
        admin.account = account;
        admin.password = encryptedPassword;

        return admin;
    }

    //== 비즈니스 메서드 ==//

    /**
     * 로그인
     */
    public boolean login(String account, String encryptedPassword) {

        if (this.account.equals(account) && this.password.equals(encryptedPassword)) {
            return true;
        }
        return false;
    }

    public void updateAll(String name, String account, String password) {
        this.name = name;
        this.account = account;
        this.password = password;
    }

    public void setRoles(List<Authority> roles) {
        this.roles = roles;
        roles.forEach(o -> o.setAdmin(this));
    }
}