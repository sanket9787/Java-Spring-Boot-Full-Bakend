package com.example.demo.gateway.api;

import com.example.demo.dto.FakeStoreLimitProductResponseDTO;
import com.example.demo.dto.LimitProductsDTO;
import org.springframework.web.bind.annotation.RequestParam;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.io.IOException;

public interface FakeStoreLimitProductApi {
    @GET("products")
    Call<FakeStoreLimitProductResponseDTO> getLimitedProducts(@Query("limit") int limit) throws IOException;
}