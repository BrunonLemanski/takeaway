package com.brunon.takeaway.controller;

import com.brunon.takeaway.model.Item;
import com.brunon.takeaway.model.Message;
import com.brunon.takeaway.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/item/{name}")
    private String getItemByName(@PathVariable String name, Model model) {
        Optional<Item> item = itemRepository.findByNameIgnoreCase(name.replace("-","").toLowerCase());
        item.ifPresent(it -> model.addAttribute("item", it));
        return item.map(it -> "item").orElse("redirect:/"); //resources/templates/item.html
    }

    @GetMapping("/admin/products")
    public String getAllItems(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);

        return "product_panel";
    }

    @GetMapping("/admin/product/{id}")
    public String getItemById(@PathVariable Long id, Model model) {
        Optional<Item> item = itemRepository.findById(id);
        item.ifPresent(it -> model.addAttribute("item", it));
        return item.map(it -> "product_edit").orElse("redirect:/admin/products");
    }

    /*@GetMapping("/admin/product/save")
    public String getSaveItem(Model model) {
        model.addAttribute("item", new Item());
        return "product_edit";
    }*/

    @PostMapping("/admin/product")
    public String saveChangesItem(@ModelAttribute Item item, Model model) {
        Optional<Item> item1 = itemRepository.findById(item.getId());

        item1.ifPresent(o -> {
            o.setName(item.getName());
            o.setDescription(item.getDescription());
            o.setPrice(item.getPrice());
            o.setShortDesc(item.getShortDesc());
            o.setImgUrl(item.getImgUrl());
            itemRepository.save(o);
        });
        return "redirect:/admin/products";
    }
}
