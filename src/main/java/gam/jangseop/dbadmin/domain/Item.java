package gam.jangseop.dbadmin.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "tblavataritemdesc")
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fditemnum")
    private Long id;

    @Column(name = "fditemname")
    private String name;

    //== 생성메서드 ==//

    public static Item createItem(String name) {
        Item item = new Item();
        item.name = name;

        return item;
    }
}
