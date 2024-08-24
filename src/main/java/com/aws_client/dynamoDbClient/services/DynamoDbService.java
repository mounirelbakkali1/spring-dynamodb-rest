package com.aws_client.dynamoDbClient.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.*;

@Service
public class DynamoDbService {

    @Autowired
    private DynamoDbClient dynamoDbClient;

    @Value("${dynamodb.table}")
    String tableName;

    /**
     * Writes an item to the DynamoDB table.
     * @param tableName The name of the DynamoDB table.
     * @param item A map representing the item to be written to the table.
     * @return The response from the DynamoDB service.
     */
    public PutItemResponse writeItem(Map<String, AttributeValue> item) {
        PutItemRequest request = PutItemRequest.builder()
                .tableName(tableName)
                .item(item)
                .build();

        return dynamoDbClient.putItem(request);
    }

    /**
     * Reads all items from the DynamoDB table.
     * @param tableName The name of the DynamoDB table.
     * @return A list of items from the table.
     */
    public List<Map<String, AttributeValue>> readAllItems() {
        List<Map<String, AttributeValue>> items = new ArrayList<>();

        ScanRequest scanRequest = ScanRequest.builder()
                .tableName(tableName)
                .build();

        ScanResponse scanResponse = dynamoDbClient.scan(scanRequest);

        items.addAll(scanResponse.items());

        return items;
    }
}
