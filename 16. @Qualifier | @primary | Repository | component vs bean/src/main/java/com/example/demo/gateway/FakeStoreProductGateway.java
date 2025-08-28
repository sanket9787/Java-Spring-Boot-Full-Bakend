package com.example.demo.gateway;

import com.example.demo.dto.FakeStoreProductResponseDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.gateway.api.FakeStoreProductApi;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class FakeStoreProductGateway implements IProductGateway{

    private final FakeStoreProductApi fakeStoreProductApi;
    public FakeStoreProductGateway(FakeStoreProductApi fakeStoreProductApi){
        this.fakeStoreProductApi = fakeStoreProductApi;
    }

    @Override
    public ProductDTO getProductById(Long id) throws Exception {
        FakeStoreProductResponseDTO response =  this.fakeStoreProductApi.getFakeProduct(id).execute().body();
        if(response == null){
            throw new Exception("Product not found");
        }

        return response.getProduct();
    }
}
