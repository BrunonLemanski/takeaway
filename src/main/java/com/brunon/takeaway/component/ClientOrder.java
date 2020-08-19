package com.brunon.takeaway.component;

import com.brunon.takeaway.model.Item;
import com.brunon.takeaway.model.Order;
import com.brunon.takeaway.model.OrderStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class ClientOrder {

    private Order order;

    public ClientOrder() {
        clear();
    }

    public Order getOrder() {
        return order;
    }


    public void add(Item item){
        order.getItems().add(item);
    }

    public void clear(){
        order = new Order();
        order.setStatus(OrderStatus.NEW);
    }

    public void remove(Item item) {
        order.getItems().remove(item);
    }
}
