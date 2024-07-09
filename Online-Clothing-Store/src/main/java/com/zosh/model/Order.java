package com.zosh.model;

import jakarta.persistence.*;

@Entity

public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    @ManyToOne
    private User customer;
}
