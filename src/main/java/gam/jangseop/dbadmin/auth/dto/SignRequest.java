package gam.jangseop.dbadmin.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SignRequest {

    private Long id;

    private String name;

    private String account;

    private String password;

}
