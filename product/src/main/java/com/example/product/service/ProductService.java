package com.example.product.service;


import com.example.product.dto.ProductRequest;
import com.example.product.dto.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse saveProduct(ProductRequest request);

    ProductResponse updateProduct(Long id, ProductRequest request);

    String deleteProduct(Long id);

    ProductResponse getProductById(Long id);

    List<ProductResponse> getAllProducts();

    List<ProductResponse> searchProduct(String productName);
}
