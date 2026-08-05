package com.example.invoice.client;


import com.example.invoice.dto.client.ProductResponse;
import com.example.invoice.exception.ProductNotFoundException;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ProductClient {

    private final WebClient.Builder builder;

    public ProductClient(WebClient.Builder builder) {
        this.builder = builder;
    }

    public ProductResponse getProduct(Long productId) {

        return builder.build()
                .get()
                .uri("http://PRODUCT-SERVICE/product/" + productId)
                .retrieve()

                .onStatus(HttpStatusCode::is4xxClientError,
                        response -> {
                            throw new ProductNotFoundException(
                                    "Product not found with id : " + productId);
                        })

                .onStatus(HttpStatusCode::is5xxServerError,
                        response -> {
                            throw new RuntimeException(
                                    "Product Service is unavailable");
                        })

                .bodyToMono(ProductResponse.class)
                .block();
    }
}