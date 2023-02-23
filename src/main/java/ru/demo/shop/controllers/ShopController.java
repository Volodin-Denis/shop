package ru.demo.shop.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.demo.shop.models.Item;
import ru.demo.shop.services.CategoriesService;
import ru.demo.shop.services.ItemsService;

import java.security.Principal;

@Controller
@RequestMapping("/shop")
public class ShopController {

    private final CategoriesService categoriesService;
    private final ItemsService itemsService;

    @Autowired
    public ShopController(CategoriesService categoriesService, ItemsService itemsService) {
        this.categoriesService = categoriesService;
        this.itemsService = itemsService;
    }

    @GetMapping()
    public String index(Model model, Principal principal){
        model.addAttribute("isLoggedIn", principal!=null);
        if(principal!=null){
            model.addAttribute("username", principal.getName());
        }

        model.addAttribute("categories",categoriesService.findAll());
        return "shop/index";
    }

    @GetMapping("/{id}")
    public String showCategory(@PathVariable("id") int categoryId, Model model, Principal principal){
        model.addAttribute("isLoggedIn", principal!=null);
        if(principal!=null){
            model.addAttribute("username", principal.getName());
        }

        model.addAttribute("category", categoriesService.findOne(categoryId));
        model.addAttribute("items", itemsService.findAllByCategoryId(categoryId));
        return "shop/category";
    }

    @GetMapping("/item/{id}")
    public String showItem(@PathVariable("id") int itemId, Model model, Principal principal){
        model.addAttribute("isLoggedIn", principal!=null);
        if(principal!=null){
            model.addAttribute("username", principal.getName());
        }

        model.addAttribute("item", itemsService.findOne(itemId));
        return "shop/item";
    }
}
