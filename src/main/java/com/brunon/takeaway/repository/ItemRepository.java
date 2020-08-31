package com.brunon.takeaway.repository;

import com.brunon.takeaway.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Optional<Item> findByNameIgnoreCase(String name);
    Item findByName(String name);
}
