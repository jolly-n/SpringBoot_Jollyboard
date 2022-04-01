package jolly.shop.controller.shop;

import jolly.shop.domain.Item;
import jolly.shop.repository.ItemRepository;
import jolly.shop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor // final이 붙은 필드를 모아 생성자를 생성해줌
@RequestMapping("/shop/items")
public class ShopItemController {

    private final ItemRepository itemRepository;
    private final ItemService itemService;

    @GetMapping("/add")
    public String addForm() {
        return "shop/addForm";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Item item) {
        itemRepository.save(item);
        return "redirect:/shop/items";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(Model model, @PathVariable Long itemId) {
        Item item = itemRepository.findById(itemId).orElse(null);
        model.addAttribute("item", item);
        return "shop/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemService.update(item, itemId);
        return "redirect:/shop/items/{itemId}";
    }

    @GetMapping
    public String items(Model model, @PageableDefault(size = 5) Pageable pageable, // 5개씩 페이징 처리
                        @RequestParam(required = false, defaultValue = "") String searchText) {
        // Page<Item> items = itemRepository.findAll(pageable);
        Page<Item> items = itemRepository.findByNameContaining(pageable, searchText);
        int startPage = Math.max(1, items.getPageable().getPageNumber() - 3);
        int endPage = Math.min(items.getPageable().getPageNumber() + 3, items.getTotalPages());
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("items", items);
        return "shop/items";
    }

    @GetMapping("/{itemId}")
    public String item(Model model, @PathVariable Long itemId) {
        Item item = itemRepository.findById(itemId).orElse(null);
        model.addAttribute("item", item);
        return "shop/item";
    }

    @PostMapping("/{itemId}")
    public String delete(@PathVariable Long itemId) {
        itemRepository.deleteById(itemId);
        return "redirect:/shop/items";
    }

}
