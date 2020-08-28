package com.brunon.takeaway.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String login;
    private String password;
    @Enumerated(EnumType.STRING)
    private AdminRole role;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

    @PrePersist
    protected void onCreate() { this.createdOn = LocalDateTime.now(); }

    @PreUpdate
    protected void onUpdated() { this.updatedOn = LocalDateTime.now(); }
}
