package com.brunon.takeaway.controller;

import com.brunon.takeaway.model.Item;
import com.brunon.takeaway.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/")
    public String home(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "home"; //resources/templates/home.html
    }
}
