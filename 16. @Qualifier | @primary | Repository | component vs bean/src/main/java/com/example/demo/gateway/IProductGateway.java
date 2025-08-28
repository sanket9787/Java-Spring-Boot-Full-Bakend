package com.example.demo.gateway;

import com.example.demo.dto.ProductDTO;

import java.io.IOException;

public interface IProductGateway {
    ProductDTO getProductById(Long id) throws Exception;
}
