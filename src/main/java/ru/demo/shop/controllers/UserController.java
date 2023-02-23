package ru.demo.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.demo.shop.models.Address;
import ru.demo.shop.security.UserDetails;
import ru.demo.shop.services.AddressesService;
import ru.demo.shop.services.UserDetailService;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserDetailService userDetailService;
    private final AddressesService addressesService;

    @Autowired
    public UserController(UserDetailService userDetailService, AddressesService addressesService) {
        this.userDetailService = userDetailService;
        this.addressesService = addressesService;
    }

    @GetMapping()
    public String showUserProfile(Model model, Principal principal){
        model.addAttribute("userDetails", userDetailService.loadUserByUsername(principal.getName()));
        return "user/profile";
    }

    @PostMapping("/address")
    public String addAddress(@RequestParam(value = "address") String address, Principal principal){
        UserDetails userDetails = userDetailService.loadUserByUsername(principal.getName());
        Address newAddress = new Address(address, userDetails.getUser());
        addressesService.save(newAddress);
        return "redirect:/user";
    }

    @PatchMapping()
    public String updateUser(){
        return "";
    }
}
