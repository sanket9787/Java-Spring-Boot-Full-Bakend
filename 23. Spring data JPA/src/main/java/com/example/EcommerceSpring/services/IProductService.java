package com.example.EcommerceSpring.services;

import com.example.EcommerceSpring.dto.ProductDTO;

public interface IProductService {

    ProductDTO getProductById(Long id) throws Exception;
    ProductDTO createProduct(ProductDTO dto);
}
