package com.portal.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.portal.DTO.ProductsDTO;
import com.portal.mapper.ProductsMapper;
import com.portal.pojo.BusinessUser;
import com.portal.pojo.ProductType;
import com.portal.pojo.Products;
import com.portal.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    private ProductsMapper productsMapper;


    @Override
    public IPage<ProductsDTO> selectAll(Long page, Long size) {
        MPJLambdaWrapper<Products> wrapper = new MPJLambdaWrapper<Products>()
                .selectAll(Products.class)
                .selectAs(ProductType::getName, ProductsDTO::getProductType)
                .selectAs((BusinessUser::getUsername), ProductsDTO::getBusinessName)
                .leftJoin(ProductType.class, ProductType::getId, Products::getTypeId)
                .leftJoin(BusinessUser.class, BusinessUser::getId, Products::getBusinessId);
        return productsMapper.selectJoinPage(new Page<>(page, size), ProductsDTO.class, wrapper);
    }

}
