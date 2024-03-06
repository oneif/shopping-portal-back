package com.portal.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.portal.DTO.ProductsDTO;

public interface ProductsService {

    IPage<ProductsDTO> selectAll(Long page, Long size);

}
