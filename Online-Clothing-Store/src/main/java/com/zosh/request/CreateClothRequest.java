package com.zosh.request;

import com.zosh.model.Category;
import lombok.Data;

import java.util.List;

@Data
public class CreateClothRequest {
    private String name;
    private String description;
    private Long price;

    private Category category;
    private List<String> images;
}
