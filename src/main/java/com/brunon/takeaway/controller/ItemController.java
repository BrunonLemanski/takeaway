package com.brunon.takeaway.controller;

import com.brunon.takeaway.model.Item;
import com.brunon.takeaway.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/{name}")
    private String getItem(@PathVariable String name, Model model) {
        Optional<Item> item = itemRepository.findByNameIgnoreCase(name.replace("-","").toLowerCase());
        item.ifPresent(it -> model.addAttribute("item", it));
        return item.map(it -> "item").orElse("redirect:/"); //resources/templates/item.html
    }
}
