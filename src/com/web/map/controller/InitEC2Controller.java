package com.web.map.controller;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.ec2.AmazonEC2Client;

public class InitEC2Controller {
    public static AmazonEC2Client         ec2;
//    private AmazonS3           s3;
    public static AmazonDynamoDBClient dynamo;

    public static void init(){
        if (ec2 == null) {
            AWSCredentialsProvider credentialsProvider = new ClasspathPropertiesFileCredentialsProvider();
            ec2    = new AmazonEC2Client(credentialsProvider);
//            s3     = new AmazonS3Client(credentialsProvider);
            dynamo = new AmazonDynamoDBClient(credentialsProvider);
        }
    }

}
