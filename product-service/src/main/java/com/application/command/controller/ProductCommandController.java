package com.application.command.controller;

import com.application.command.CreateProductCommand;
import com.application.dto.CreateProductRequestDto;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductCommandController {
    private final CommandGateway commandGateway;

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody @Valid CreateProductRequestDto createProductRestModel) {

        CreateProductCommand createProductCommand = CreateProductCommand.builder()
                .price(createProductRestModel.getPrice())
                .quantity(createProductRestModel.getQuantity())
                .title(createProductRestModel.getTitle())
                .productId(UUID.randomUUID().toString())
                .build();

        return ResponseEntity.ok(commandGateway.sendAndWait(createProductCommand));
    }
}
