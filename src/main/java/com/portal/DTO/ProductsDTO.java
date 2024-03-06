package com.portal.DTO;

import com.portal.pojo.Products;
import lombok.Data;

@Data
public class ProductsDTO extends Products {

    private String productType;
    private String businessName;
}
