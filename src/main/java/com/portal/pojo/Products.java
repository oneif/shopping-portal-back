package com.portal.pojo;

import lombok.Data;

@Data
public class Products {
    private Long id;
    private String name;
    private String img;
    private String description;
    private Double price;
    private String unit;
    private String type;
    private Long typeId;
    private Long businessId;
    private Long count;
}

