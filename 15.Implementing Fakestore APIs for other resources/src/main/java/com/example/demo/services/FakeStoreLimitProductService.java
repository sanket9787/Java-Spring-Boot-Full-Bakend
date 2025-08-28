package com.example.demo.services;

import com.example.demo.dto.LimitProductsDTO;
import com.example.demo.gateway.ILimitProductGateway;
import com.example.demo.gateway.IProductGateway;
import com.example.demo.gateway.api.FakeStoreLimitProductApi;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class FakeStoreLimitProductService implements ILimitProductService{

    private final ILimitProductGateway limitProductGateway;

    public FakeStoreLimitProductService(ILimitProductGateway limitProductGateway) {
        this.limitProductGateway = limitProductGateway;
    }

    @Override
    public List<LimitProductsDTO> getLimitedProducts(int limit) throws IOException {
        return this.limitProductGateway.getLimitedProducts(limit);
    }
}
