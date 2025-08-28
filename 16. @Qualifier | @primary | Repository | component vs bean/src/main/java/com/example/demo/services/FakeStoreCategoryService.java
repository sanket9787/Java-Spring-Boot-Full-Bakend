package com.example.demo.services;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.gateway.ICategoryGateway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;

@Service
public class FakeStoreCategoryService implements ICategoryService{

    private final ICategoryGateway categoryGateway;

    //We can use @Qualifier to mention which class object which implemented the same interface we want while doing the constructor injection
    public FakeStoreCategoryService(
            @Qualifier("FakeStoreRestTemplateGateway") ICategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    @Override
    public List<CategoryDTO> getAllCategories() throws IOException {
        return this.categoryGateway.getAllCategories();
    }
}
