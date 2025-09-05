package com.example.EcommerceSpring.services;

import com.example.EcommerceSpring.dto.ProductDTO;
import com.example.EcommerceSpring.entity.Product;
import com.example.EcommerceSpring.mapper.ProductMapper;
import com.example.EcommerceSpring.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService{
    private final ProductRepository repo;

    public ProductService(ProductRepository repo){
        this.repo = repo;
    }

    @Override
    public ProductDTO getProductById(Long id) throws Exception{
//        return repo.findById(id)
//                .map(ProductMapper::toDto)
//                .orElseThrow() -> new Exception(("Product not found"));
          Product product = repo.findById(id)
                  .orElseThrow(() -> new Exception("Product not found"));
          ProductDTO dto = ProductMapper.toDto(product);
          return dto;
    }

    @Override
    public ProductDTO createProduct(ProductDTO dto) {
        Product saved =  repo.save(ProductMapper.toEntity(dto));
        return ProductMapper.toDto(saved);
    }
}
