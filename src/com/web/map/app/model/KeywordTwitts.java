package com.web.map.app.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMarshalling;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.web.map.controller.DynamoDbController;

@DynamoDBTable(tableName=DynamoDbController.dynamoDbTableName)
@XmlRootElement
public class KeywordTwitts {
    String id;
    String keyword;
    Twitts twitts;
    @DynamoDBHashKey(attributeName="Id")
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    @DynamoDBAttribute(attributeName="Keyword")
    public String getKeyword() {
        return keyword;
    }
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    @DynamoDBAttribute(attributeName="Twitts")
    @DynamoDBMarshalling(marshallerClass = TwittsMarshaller.class)
    public Twitts getTwitts() {
        return twitts;
    }
    public void setTwitts(Twitts twitts) {
        this.twitts = twitts;
    }

    
}
