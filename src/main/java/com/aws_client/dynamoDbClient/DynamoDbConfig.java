package com.aws_client.dynamoDbClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration
public class DynamoDbConfig {

    @Bean
    public DynamoDbClient dynamoDbClient() {
        return DynamoDbClient.builder()
                .region(Region.US_EAST_1)  // Set your desired region
                //.credentialsProvider(ProfileCredentialsProvider.create())                
                .credentialsProvider(DefaultCredentialsProvider.create()) // for cloud env (where ec2 uses the IAM role)
                .build();
    }
}
