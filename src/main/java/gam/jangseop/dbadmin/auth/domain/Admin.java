package gam.jangseop.dbadmin.auth.domain;

import gam.jangseop.dbadmin.auth.util.SHA256;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Admin {

    @Id
    @GeneratedValue
    @Column(name = "ADMIN_ID")
    private Long id;

    private String name;

    private String account;

    private String password;

    //== 생성 메서드 ==//

    public static Admin createAdmin(String name, String account, String encryptedPassword) {

        Admin admin = new Admin();
        SHA256 sha256 = new SHA256();

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
}