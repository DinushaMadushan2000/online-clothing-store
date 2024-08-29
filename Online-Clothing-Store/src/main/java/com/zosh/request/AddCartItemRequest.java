package com.zosh.request;

import lombok.Data;


@Data
public class AddCartItemRequest {

    private Long clothId;
    private int quantity;
}
