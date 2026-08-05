package com.example.invoice.client;



import com.example.invoice.dto.client.CustomerResponse;
import com.example.invoice.exception.CustomerNotFoundException;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class CustomerClient {

    private final WebClient.Builder builder;

    public CustomerClient(WebClient.Builder builder) {
        this.builder = builder;
    }

    public CustomerResponse getCustomer(Long customerId) {

        return builder.build()
                .get()
                .uri("http://CUSTOMER-SERVICE/customer/" + customerId)
                .retrieve()

                .onStatus(HttpStatusCode::is4xxClientError,
                        response -> {
                            throw new CustomerNotFoundException(
                                    "Customer not found with id : " + customerId);
                        })

                .onStatus(HttpStatusCode::is5xxServerError,
                        response -> {
                            throw new RuntimeException(
                                    "Customer Service is unavailable");
                        })

                .bodyToMono(CustomerResponse.class)
                .block();
    }
}