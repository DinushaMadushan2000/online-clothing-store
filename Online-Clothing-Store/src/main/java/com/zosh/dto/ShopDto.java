package com.zosh.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.List;

@Data
public class ShopDto {
    private String title;
@Column(length = 1000)
    private List<String> images;
    private String discription;
    private Long id;
}
