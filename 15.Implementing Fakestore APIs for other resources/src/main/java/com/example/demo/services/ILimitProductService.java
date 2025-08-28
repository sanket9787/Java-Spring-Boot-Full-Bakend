package com.example.demo.services;

import com.example.demo.dto.LimitProductsDTO;

import java.io.IOException;
import java.util.List;

public interface ILimitProductService {
    List<LimitProductsDTO> getLimitedProducts(int limit) throws IOException;
}
