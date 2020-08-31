package com.brunon.takeaway.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private String shortDesc;
    @Column(length = 2048)
    private String description;
    private double price;
    private String imgUrl;
}
