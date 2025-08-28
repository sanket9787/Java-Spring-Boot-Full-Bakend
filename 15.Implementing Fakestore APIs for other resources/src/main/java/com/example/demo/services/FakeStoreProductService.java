package com.example.demo.services;

import com.example.demo.dto.ProductDTO;
import com.example.demo.gateway.IProductGateway;
import org.springframework.stereotype.Service;

@Service
public class FakeStoreProductService implements IProductService{

    private final IProductGateway productGateway;

    public FakeStoreProductService(IProductGateway productGateway){
        this.productGateway = productGateway;
    }

    @Override
    public ProductDTO getProductById(Long id) throws Exception {
        return this.productGateway.getProductById(id);
    }
}
