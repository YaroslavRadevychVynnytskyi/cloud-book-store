package com.nerdysoft.controller;

import com.nerdysoft.axon.command.CreateOrderCommand;
import com.nerdysoft.dto.OrderRequestDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersCommandController {
    private final CommandGateway commandGateway;

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderRequestDto requestDto) {
        CreateOrderCommand createOrderCommand = new CreateOrderCommand(UUID.randomUUID().toString(), requestDto);

        return ResponseEntity.ok(commandGateway.sendAndWait(createOrderCommand));
    }
}
