package jolly.shop.controller.shop;

import jolly.shop.domain.Item;
import jolly.shop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor // final이 붙은 필드를 모아 생성자를 생성해줌
@RequestMapping("/shop")
public class ShopItemController {

    private final ItemRepository itemRepository;

    @GetMapping("/add")
    public String addForm() {
        return "shop/addForm";
    }

    @PostMapping("/add")
    public String add() {
        return "shop/items";
    }

    @GetMapping("/edit")
    public String editForm() {
        return "shop/editForm";
    }

    @PostMapping("/edit")
    public String edit() {
        return "shop/item";
    }

    @GetMapping("/items")
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "shop/items";
    }

    @GetMapping("/item")
    public String item() {
        return "shop/item";
    }

}
