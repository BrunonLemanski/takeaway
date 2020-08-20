package com.brunon.takeaway.controller;

import com.brunon.takeaway.model.Item;
import com.brunon.takeaway.model.Order;
import com.brunon.takeaway.model.OrderStatus;
import com.brunon.takeaway.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("")
    public String adminHome(Model model){
        List<Order> orders = orderRepository.findAllByStatus(OrderStatus.NEW);
        model.addAttribute("new_orders", orders.size());
        return "admin"; //resources/templates/admin.html
    }

    @GetMapping("/orders")
    public String getAllOrders(@RequestParam(required = false) OrderStatus status, Model model) {
        List<Order> orders;

        if(status == null){
            orders = orderRepository.findAll();
        } else {
            orders = orderRepository.findAllByStatus(status);
        }
        model.addAttribute("orders", orders);

        return "orders_overview";
    }

    @GetMapping("/order_details/{orderId}")
    public String getOrder(@PathVariable Long orderId, Model model) {
        Optional<Order> order = orderRepository.findById(orderId);
        return order.map(o -> getOrderDetails(o, model)).orElse("redirect:/");
    }

    @PostMapping("/order_details/{id}")
    public String realizeOrder(@PathVariable Long id, Model model) {
        Optional<Order> order = orderRepository.findById(id);

        order.ifPresent(o -> {
            o.setStatus(OrderStatus.nextStatus(orderRepository.findById(id).get().getStatus()));
            orderRepository.save(o);
        });

        return order.map(o -> getOrderDetails(o, model)).orElse("redirect:/");
    }

    private String getOrderDetails(Order order, Model model) {
        model.addAttribute("order", order);
        model.addAttribute("sum", order.getItems().stream().mapToDouble(Item::getPrice).sum());
        return "order_details";
    }
}
