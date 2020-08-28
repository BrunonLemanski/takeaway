package com.brunon.takeaway.controller;

import com.brunon.takeaway.model.Item;
import com.brunon.takeaway.model.Order;
import com.brunon.takeaway.model.OrderStatus;
import com.brunon.takeaway.model.Admin;
import com.brunon.takeaway.repository.AdminRepository;
import com.brunon.takeaway.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AdminRepository adminRepository;

    @GetMapping("")
    public String adminHome(Model model){
        List<Order> orders = orderRepository.findAllByStatus(OrderStatus.NEW);
        model.addAttribute("new_orders", orders.size());
        return "admin"; //resources/templates/admin.html
    }

    @GetMapping("/users/add")
    public String addUser() {
        return "user_add_panel";
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
        return order.map(o -> getOrderDetails(o, model)).orElse("redirect:/admin");
    }

    @PostMapping("/order_details/{id}")
    public String realizeOrder(@PathVariable Long id, Model model) {
        Optional<Order> order = orderRepository.findById(id);

        order.ifPresent(o -> {
            o.setStatus(OrderStatus.nextStatus(orderRepository.findById(id).get().getStatus()));
            orderRepository.save(o);
        });

        return order.map(o -> getOrderDetails(o, model)).orElse("redirect:/admin");
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        ArrayList<Admin> admins = (ArrayList<Admin>) adminRepository.findAll();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Iterator<Admin> iterator = admins.iterator();

        if(principal instanceof UserDetails) {
            while(iterator.hasNext()) {
                Admin admin = iterator.next();

                if(admin.getLogin().equals(((UserDetails) principal).getUsername())) {
                    iterator.remove();
                }
            }
        }

        model.addAttribute("admins", admins);
        return "users_admin_panel";
    }

    @GetMapping("/users/edit")
    public String getUser(@RequestParam Long id, Model model) {
        Optional<Admin> admin = adminRepository.findById(id);
        return admin.map(o -> getUser(o, model)).orElse("redirect:/admin");
    }

    @PostMapping("/users/edit")
    public String saveUser(@ModelAttribute Admin admin) {
        Optional<Admin> user = adminRepository.findById(admin.getId());

        user.ifPresent(o -> {
            o.setLogin(admin.getLogin());
            o.setPassword(admin.getPassword());
            o.setRole(admin.getRole());
            adminRepository.save(o);
        });

        if(user.isEmpty()) {
            adminRepository.save(admin);
        }

        return "redirect:/admin/users";
    }

    private String getUser(Admin admin, Model model) {
        model.addAttribute("admin", admin);
        return "user_edit_panel";
    }

    private String getOrderDetails(Order order, Model model) {
        model.addAttribute("order", order);
        model.addAttribute("sum", order.getItems().stream().mapToDouble(Item::getPrice).sum());
        return "order_details";
    }

}
