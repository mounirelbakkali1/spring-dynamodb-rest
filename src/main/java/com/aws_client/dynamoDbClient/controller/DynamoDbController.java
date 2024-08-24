package com.aws_client.dynamoDbClient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aws_client.dynamoDbClient.services.DynamoDbService;
import com.aws_client.dynamoDbClient.utils.ItemDtoMapper;

import java.util.List;

@RestController
@RequestMapping("/items")
public class DynamoDbController {

    @Autowired
    private DynamoDbService dynamoDbService;

    @PostMapping
    public ResponseEntity<?> writeItem(@RequestBody DynamoDbItemDTO item) {
        dynamoDbService.writeItem(ItemDtoMapper.toMap(item));
        return ResponseEntity.ok().body("Created!");
    }

    @GetMapping
    public ResponseEntity<List<DynamoDbItemDTO>> readAllItems() {
        return ResponseEntity.ok(ItemDtoMapper.toDto(dynamoDbService.readAllItems()));
    }
}

