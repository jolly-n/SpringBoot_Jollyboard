package jolly.shop.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity // 데이터베이스 연동을 위한 모델 객체임을 명시
@Getter @Setter
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // pk값임을 명시
    private Long id; // 상품ID
    private String name; // 상품명
    private Integer price; // 가격
    private Integer quantity; // 수량

}
