package com.web.map.controller;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.DescribeTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.TableDescription;
import com.amazonaws.services.dynamodbv2.model.TableStatus;
import com.web.map.app.model.KeywordTwitts;

public class DynamoDbController {
    private AmazonDynamoDBClient amazonDynamoDBClient = null;
    public final static String dynamoDbTableName="dynamoDbTwitts";
//    public DynamoDbController withDbClient(AWSCredentials awsCredentials)
//    {
//        this.amazonDynamoDBClient = new AmazonDynamoDBClient(awsCredentials);
//        return this;
//    }
    public DynamoDbController withDbClient(AmazonDynamoDBClient dbClient)
    {
        this.amazonDynamoDBClient = dbClient;
        return this;
    }
    public boolean createDynamoDbTable(){
        System.out.println("Trying to create table: " + dynamoDbTableName);
        try {
            ArrayList<AttributeDefinition> attributeDefinitions= new ArrayList<AttributeDefinition>();
            attributeDefinitions.add(new AttributeDefinition().withAttributeName("Id").withAttributeType("S"));
            
            ArrayList<KeySchemaElement> ks = new ArrayList<KeySchemaElement>();
            ks.add(new KeySchemaElement().withAttributeName("Id").withKeyType(KeyType.HASH));
              
            ProvisionedThroughput provisionedThroughput = new ProvisionedThroughput()
                            .withReadCapacityUnits(10L)
                            .withWriteCapacityUnits(10L);
                    
            CreateTableRequest request = new CreateTableRequest()
                            .withTableName(dynamoDbTableName)
                            .withAttributeDefinitions(attributeDefinitions)
                            .withKeySchema(ks)
                            .withProvisionedThroughput(provisionedThroughput);
                
            this.amazonDynamoDBClient.createTable(request);
            System.out.println("Request sent.");
            
            while (true)
            {
                System.out.println("Checking state ...");
                TableDescription tableDescription = this.amazonDynamoDBClient.describeTable(new DescribeTableRequest()
                                                                                .withTableName(dynamoDbTableName)).getTable();
                String status = tableDescription.getTableStatus();
                if (status.equals(TableStatus.ACTIVE.toString()))
                {
                    System.out.println("State = " + status);
                    break;
                }
                else
                {
                    System.out.println("State = " + status + ". Sleeping for 10s ...");
                    try{Thread.sleep(10 * 1000);} catch (InterruptedException e) {e.printStackTrace();}
                }
            }
            System.out.println("Table " + dynamoDbTableName + " created.");
            return true;
        }
        catch(AmazonServiceException e)
        {
//            e.printStackTrace();
            System.err.println("Table exists and not created a new one!");
            return false;
        }
        catch (AmazonClientException e)
        {
//            e.printStackTrace();
            System.err.println("Local Exception!");
            return false;
        }
    }
    public void saveKeywordTwitts(KeywordTwitts keywordTwitts)
    {
        System.out.println("Trying to save video details ...");
        try
        {           
            DynamoDBMapper mapper = new DynamoDBMapper(this.amazonDynamoDBClient);
            mapper.save(keywordTwitts);         
            System.out.println("Saved.");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public KeywordTwitts getKeywordTwittsById(String id)
    {
        KeywordTwitts keyTwitts = null;
        System.out.println("Checking if video record exixts with id: '" + id + "' ...");
        try
        {           
            DynamoDBMapper mapper = new DynamoDBMapper(this.amazonDynamoDBClient);
            keyTwitts = mapper.load(KeywordTwitts.class, id);
            
            if (keyTwitts == null)
            {
                System.out.println("No such record found.");
            }
            else
            {
                System.out.println("Retrieved.");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return keyTwitts;
    }
    
    public List<KeywordTwitts> getAllKeyTwitts()
    {
        System.out.println("Trying to fetch all video records ...");
        List<KeywordTwitts> scannedKeyTwitts = new ArrayList<KeywordTwitts>();
        List<KeywordTwitts> keyTwitts = new ArrayList<KeywordTwitts>();
        try
        {           
            DynamoDBMapper mapper = new DynamoDBMapper(this.amazonDynamoDBClient);
            DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
            
            scannedKeyTwitts = mapper.scan(KeywordTwitts.class, scanExpression);
            System.out.println("Retrieved " + scannedKeyTwitts.size() + " record(s).");
            
            keyTwitts.addAll(scannedKeyTwitts);
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return keyTwitts;
    }
    
    public void deleteKeyTwittsById(String id)
    {
        try
        {
            KeywordTwitts keyTwitts = getKeywordTwittsById(id);
            if (keyTwitts == null)
            {
                System.out.println("Nothing to delete.");
            }
            else
            {
                System.out.println("Trying to delete record with id: '" + id + "' ...");
                DynamoDBMapper mapper = new DynamoDBMapper(this.amazonDynamoDBClient);
                mapper.delete(keyTwitts);
                System.out.println("Deleted.");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void dynamoDbTableName(String string) {
        // TODO Auto-generated method stub
        
    }
}
