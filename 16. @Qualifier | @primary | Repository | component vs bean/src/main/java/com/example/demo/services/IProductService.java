package com.example.demo.services;

import com.example.demo.dto.ProductDTO;

public interface IProductService {
    ProductDTO getProductById(Long id) throws Exception;
}
