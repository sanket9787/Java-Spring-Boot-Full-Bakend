package com.example.EcommerceSpring.gateway;

import com.example.EcommerceSpring.dto.FakeStoreProductResponseDTO;
import com.example.EcommerceSpring.dto.ProductDTO;
import com.example.EcommerceSpring.gateway.api.FakeStoreProductApi;
import org.springframework.stereotype.Component;

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
