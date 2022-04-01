package jolly.shop.repository;

import jolly.shop.domain.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
    // 검색
    Page<Item> findByNameContaining(Pageable pageable, String name);
}
