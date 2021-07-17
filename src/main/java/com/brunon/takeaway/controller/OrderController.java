package com.brunon.takeaway.controller;

import com.brunon.takeaway.component.ClientOrder;
import com.brunon.takeaway.model.Item;
import com.brunon.takeaway.model.Message;
import com.brunon.takeaway.model.Order;
import com.brunon.takeaway.repository.ItemRepository;
import com.brunon.takeaway.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
@RequestMapping("/order")
public class OrderController {

    private ClientOrder clientOrder;
    private OrderRepository orderRepository;
    private ItemRepository itemRepository;

    @Autowired
    public OrderController(ClientOrder clientOrder, ItemRepository itemRepository, OrderRepository orderRepository) {
        this.clientOrder = clientOrder;
        this.itemRepository = itemRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/add")
    public String addItemToOrder(@RequestParam Long itemId, Model model) {
        Optional<Item> item = itemRepository.findById(itemId);
        item.ifPresent(clientOrder::add);
        if(item.isPresent()) {
            model.addAttribute("message", new Message("Dodano", "Przedmiot " + item.get().getName() + " został dodany do koszyka"));
        } else {
            model.addAttribute("message", new Message("Nie dodano", "Nie udało się dodać przedmiotu " + item.get().getName() + " do koszyka"));
        }

        return "message"; //resources/templates/message.html
    }

    @GetMapping("")
    public String getOrder(Model model, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("phone")) {
                    model.addAttribute("phone", cookie.getValue());
                }
                if (cookie.getName().equals("address")) {
                    model.addAttribute("address", cookie.getValue().replaceAll("_", " ").replaceAll("[.]", ","));
                }
            }
        }

        model.addAttribute("order", clientOrder.getOrder());
        model.addAttribute("sum", clientOrder
        .getOrder()
        .getItems()
        .stream()
        .mapToDouble(Item::getPrice)
        .sum());

        return "order"; //resources/templates/order.html
    }

    @GetMapping("/remove")
    public String removeItemFromOrder(@RequestParam Long itemId, Model model) {
        Optional<Item> item = itemRepository.findById(itemId);
        item.ifPresent(clientOrder::remove);
        if (item.isPresent()) {
            model.addAttribute("message", new Message("Usunięto", "Przedmiot " + item.get().getName() + " został usunięty z koszyka"));
        } else {
            model.addAttribute("message", new Message("Błąd", "Wystąpił błąd podczas usuwania pozycji zamówienia"));
        }

        return "message";
    }

    @PostMapping("/save")
    public String saveOrderInDatabase(@RequestParam String address, @RequestParam String phoneNumber, Model model, HttpServletResponse response) {
        Cookie cookie = new Cookie("phone", phoneNumber.replaceAll(" ", ""));
        Cookie cookie1 = new Cookie("address", address.replaceAll(" ", "_").replaceAll(",", "."));
        response.addCookie(cookie);
        response.addCookie(cookie1);

        Order order = clientOrder.getOrder();
        order.setAddress(address);
        order.setTelephone(phoneNumber);
        orderRepository.save(order);
        clientOrder.clear();

        model.addAttribute("message", new Message("Gotowe!", "Zamówienie zostało przekazane do realizacji :)"));

        return "message";
    }
}
