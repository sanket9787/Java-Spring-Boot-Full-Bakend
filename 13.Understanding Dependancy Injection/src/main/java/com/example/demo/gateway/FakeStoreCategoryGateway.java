package com.example.demo.gateway;

import com.example.demo.dto.CategoryDTO;

import com.example.demo.dto.FakeStoreCategoryResponseDTO;
import com.example.demo.gateway.api.FakeStoreCategoryApi;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FakeStoreCategoryGateway implements ICategoryGateway {

    private final FakeStoreCategoryApi fakeStoreCategoryApi;

    public FakeStoreCategoryGateway(FakeStoreCategoryApi fakeStoreCategoryApi) {
        this.fakeStoreCategoryApi = fakeStoreCategoryApi;
    }

    @Override
    public List<CategoryDTO> getAllCategories() throws IOException {
        FakeStoreCategoryResponseDTO response = this.fakeStoreCategoryApi.getAllFakeCategories().execute().body();
        if(response == null){
            throw new IOException("Failed to fetch categories form Fakestore API");
        }
        System.out.println("hello there");
        return response.getCategories()
                .stream()
                .map(category -> CategoryDTO.builder()
                        .name(category)
                        .build())
                .collect(Collectors.toList());
    }
}
