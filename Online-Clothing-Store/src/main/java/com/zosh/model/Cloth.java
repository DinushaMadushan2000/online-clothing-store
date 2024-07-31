package com.zosh.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity

public class Cloth {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    private Long price;

    @ManyToOne
    private Category clothCategory;

    @Column(length = 1000)
    @ElementCollection

    private List<String> images;

    private boolean available;

    @ManyToMany
    private List<ProductDetails> details=new ArrayList<>();



}
