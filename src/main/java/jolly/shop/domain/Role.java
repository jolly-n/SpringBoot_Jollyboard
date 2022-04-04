package jolly.shop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
public class Role {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String authority;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

}
