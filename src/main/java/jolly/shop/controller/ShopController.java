package jolly.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shop")
public class ShopController {

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
    public String items() {
        return "shop/items";
    }

    @GetMapping("/item")
    public String item() {
        return "shop/item";
    }

}
