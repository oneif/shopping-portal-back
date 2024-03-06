package com.portal.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.portal.DTO.ProductsDTO;
import com.portal.pojo.PageResult;
import com.portal.service.ProductsService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductsController {

    @Resource
    private ProductsService productsService;


    @RequestMapping("/list")
    public PageResult productList(String page, String size) {
        IPage<ProductsDTO> productsDTOIPage = productsService.selectAll(Long.valueOf(page), Long.valueOf(size));
        return PageResult.success("成功", productsDTOIPage.getRecords(), Integer.parseInt(page), Integer.parseInt(size), Math.toIntExact(productsDTOIPage.getTotal()), Math.toIntExact(productsDTOIPage.getPages()));
    }

}
