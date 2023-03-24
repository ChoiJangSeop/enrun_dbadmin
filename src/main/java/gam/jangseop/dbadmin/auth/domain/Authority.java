package gam.jangseop.dbadmin.auth.domain;

import jakarta.persistence.*;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.stereotype.Component;

@Entity
@Getter
@Builder @AllArgsConstructor @NoArgsConstructor
public class Authority {

    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id
    @Column(name = "AUTHORITY_ID")
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "admin")
    @JsonIgnore
    private Admin admin;

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

}
