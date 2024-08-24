package com.aws_client.dynamoDbClient.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aws_client.dynamoDbClient.controller.DynamoDbItemDTO;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

public class ItemDtoMapper {
    
    public static Map<String, AttributeValue> toMap(DynamoDbItemDTO item){
        Map<String, AttributeValue> itemMap = new HashMap<>();
        itemMap.put("id", AttributeValue.builder().s(item.getId()).build());
        itemMap.put("name", AttributeValue.builder().s(item.getName()).build());
        itemMap.put("category", AttributeValue.builder().s(item.getCategory()).build());
        itemMap.put("createdAt", AttributeValue.builder().n(item.getCreatedAt()).build());
        return itemMap;
    }

    public static List<DynamoDbItemDTO> toDto(List<Map<String, AttributeValue>> items){
        return items.stream().map(item -> {
            DynamoDbItemDTO dto = new DynamoDbItemDTO();
            dto.setId(item.get("id").s()); 
            dto.setName(item.get("name").s()); 
            dto.setCategory(item.get("category").s());
            dto.setCreatedAt(item.get("createdAt").n());
            return dto; 
        }).toList();
    }
}
