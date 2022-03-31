package jolly.shop.service;

import jolly.shop.domain.Item;
import jolly.shop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    // 상품 수정
    public void update(Item updateItem, Long itemId) {
        Item findItem = itemRepository.findById(itemId).orElse(null);
        findItem.setName(updateItem.getName());
        findItem.setPrice(updateItem.getPrice());
        findItem.setQuantity(updateItem.getQuantity());
        itemRepository.save(findItem);
    }

}
