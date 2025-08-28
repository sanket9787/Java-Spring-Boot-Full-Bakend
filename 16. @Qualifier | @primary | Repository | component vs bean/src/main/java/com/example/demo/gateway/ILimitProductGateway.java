package com.example.demo.gateway;

import com.example.demo.dto.LimitProductsDTO;

import java.io.IOException;
import java.util.List;

public interface ILimitProductGateway {
    List<LimitProductsDTO> getLimitedProducts(int limit) throws IOException;
}
