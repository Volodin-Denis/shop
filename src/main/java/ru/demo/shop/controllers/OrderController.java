package ru.demo.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.demo.shop.models.*;
import ru.demo.shop.security.UserDetails;
import ru.demo.shop.services.*;

import java.security.Principal;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final UserDetailService userDetailService;
    private final ItemsService itemsService;
    private final OrdersService ordersService;
    private final OrderedItemsService orderedItemsService;

    @Autowired
    public OrderController(UserDetailService userDetailService, ItemsService itemsService, OrdersService ordersService,
                           OrderedItemsService orderedItemsService) {
        this.userDetailService = userDetailService;
        this.itemsService = itemsService;
        this.ordersService = ordersService;
        this.orderedItemsService = orderedItemsService;
    }

    @GetMapping("/cart")
    public String getCart(Model model, Principal principal, @ModelAttribute("address") Address address ){
        UserDetails userDetails = userDetailService.loadUserByUsername(principal.getName());
        model.addAttribute("cart", ordersService.getCart(userDetails.getUser()));
        model.addAttribute("user", userDetails.getUser());
        return "orders/cart";
    }

    @PatchMapping("/cart")
    public String updateCart(@RequestParam(value = "itemId") int itemId,
                             @RequestParam(value = "quantity") int quantity,
                             Principal principal){
        Order cart = ordersService.getCart(userDetailService.loadUserByUsername(principal.getName()).getUser());
        Item item = itemsService.findOne(itemId);
        ordersService.updateCart(cart, item, quantity);
        return "redirect:/orders/cart";
    }

    @DeleteMapping("/cart")
    public String deleteFormCart(@RequestParam(value = "itemId") int itemId, Principal principal){
        Order cart = ordersService.getCart(userDetailService.loadUserByUsername(principal.getName()).getUser());
        Item item = itemsService.findOne(itemId);
        orderedItemsService.deleteFromCart(cart, item);
        return "redirect:/orders/cart";
    }

    @PostMapping("/cart")
    public String checkout(@ModelAttribute("address") Address address, Principal principal){
        Order cart = ordersService.getCart(userDetailService.loadUserByUsername(principal.getName()).getUser());
        cart.setAddress(address);
        cart.setStatus(Status.NOT_VERIFIED);
        ordersService.save(cart);
        return "redirect:/orders/history";
    }

    @GetMapping("/history")
    public String getOrderHistory(Model model, Principal principal){
        UserDetails userDetails = userDetailService.loadUserByUsername(principal.getName());
        model.addAttribute("orderList",userDetails.getUser().getOrders());
        return "orders/history";
    }

}
